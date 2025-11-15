package com.hsicen.plugin

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationParameters
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.objectweb.asm.ClassVisitor

class Hencoder : Plugin<Project> {

  override fun apply(target: Project) {
    val extension = target.extensions.create("hsicen", HsicenExtension::class.java)
    target.afterEvaluate {//读取配置完成后再执行
      println("Hi ${extension.name}")
    }

    val componentsExtension = target.extensions.getByType(AndroidComponentsExtension::class.java)
    componentsExtension.onVariants { variant ->
      variant.instrumentation.transformClassesWith(
        HencoderTransform::class.java, InstrumentationScope.ALL
      ) {

      }

      variant.instrumentation.setAsmFramesComputationMode(
        FramesComputationMode.COMPUTE_FRAMES_FOR_INSTRUMENTED_METHODS
      )
    }
  }
}

// 参数定义
interface HencoderParams : InstrumentationParameters

abstract class HencoderTransform : AsmClassVisitorFactory<HencoderParams> {

  override fun createClassVisitor(
    classContext: ClassContext,
    nextClassVisitor: ClassVisitor
  ): ClassVisitor {
    // 使用字节码工具对class文件进行处理
    val name = classContext.currentClassData.className
    println("Find class: $name")
    return nextClassVisitor
  }

  // 是否处理当前 class
  override fun isInstrumentable(classData: ClassData) = true
}


open class HsicenExtension {
  var name = "Hsicen"
}