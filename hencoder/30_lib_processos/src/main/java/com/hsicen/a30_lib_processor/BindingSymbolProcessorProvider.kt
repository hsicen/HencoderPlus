package com.hsicen.a30_lib_processor

import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSValueArgument
import com.google.devtools.ksp.validate
import com.hsicen.a30_lib_annotation.BindView
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import java.io.OutputStreamWriter
import javax.lang.model.element.Modifier

class BindingSymbolProcessorProvider : SymbolProcessorProvider {
  override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
    return BindingSymbolProcessor(environment)
  }
}

private class BindingSymbolProcessor(
  private val environment: SymbolProcessorEnvironment
) : SymbolProcessor {

  override fun process(resolver: Resolver): List<KSPropertyDeclaration> {
    val symbols = resolver
      .getSymbolsWithAnnotation(BindView::class.qualifiedName.orEmpty())
      .filterIsInstance<KSPropertyDeclaration>()
      .toList()

    val invalidSymbols = symbols.filterNot { it.validate() }
    val validSymbols = symbols.filter { it.validate() }
    validSymbols.groupBy { it.parentDeclaration as? KSClassDeclaration }
      .forEach { (classDeclaration, properties) ->
        if (classDeclaration != null && properties.isNotEmpty()) {
          generateBinding(classDeclaration, properties)
        }
      }

    return invalidSymbols
  }

  private fun generateBinding(
    classDeclaration: KSClassDeclaration,
    properties: List<KSPropertyDeclaration>
  ) {
    val pkg = classDeclaration.packageName.asString()
    val className = classDeclaration.simpleName.asString()
    val generatedClassName = "${className}Binding"
    val targetClass = ClassName.get(pkg, className)

    val constructor = MethodSpec.constructorBuilder()
      .addModifiers(Modifier.PUBLIC)
      .addParameter(targetClass, "activity")

    properties.forEach { property ->
      constructor.addStatement(
        "activity.\$N = activity.findViewById(\$L)",
        property.simpleName.asString(),
        property.bindViewIdExpression()
      )
    }

    val generatedClass = TypeSpec.classBuilder(generatedClassName)
      .addModifiers(Modifier.PUBLIC)
      .addMethod(constructor.build())
      .build()

    val dependencies = Dependencies(
      aggregating = false,
      *properties.mapNotNull { it.containingFile }.toTypedArray()
    )
    val output = environment.codeGenerator.createNewFile(
      dependencies = dependencies,
      packageName = pkg,
      fileName = generatedClassName,
      extensionName = "java"
    )

    OutputStreamWriter(output, Charsets.UTF_8).use { writer ->
      JavaFile.builder(pkg, generatedClass).build().writeTo(writer)
    }
  }

  private fun KSPropertyDeclaration.bindViewIdExpression(): String {
    val annotation = annotations.first {
      it.annotationType.resolve().declaration.qualifiedName?.asString() == BindView::class.qualifiedName
    }
    val value = annotation.arguments.firstValue()
    return if (value is Number) {
      value.toInt().toString()
    } else if (value is String) {
      "R.id.$value"
    } else {
      "R.id.${fallbackViewIdName()}"
    }
  }

  private fun List<KSValueArgument>.firstValue(): Any? {
    return firstOrNull { it.name?.asString() == "value" }?.value ?: first().value
  }

  private fun KSPropertyDeclaration.fallbackViewIdName(): String {
    val propertyName = simpleName.asString()
    if (propertyName.length > 1 && propertyName[0] == 'm' && propertyName[1].isUpperCase()) {
      return propertyName.substring(1).replaceFirstChar { it.lowercaseChar() }
    }
    return propertyName
  }
}
