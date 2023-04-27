import java.io.FileInputStream
import java.util.Properties

plugins {
  id("comm.app-module")
  id("Hencoder")
}

//自定义插件 方式一 直接在groovy文件中定义
open class HelloWorldExtension {
  var say = "Hello World"
}

class HelloWorld : Plugin<Project> {
  override fun apply(target: Project) {
    //这里定义的 HelloWorld 暂时还没有找到使用的方法
    val helloWorld = target.extensions.create<HelloWorldExtension>("HelloWorld")
    target.afterEvaluate {
      println("Plugin say ${helloWorld.say}")
    }
  }
}

//引用自定义插件 有以下两种方式使用
apply<HelloWorld>()
the<HelloWorldExtension>().say = "Hello Groovy 2"
configure<HelloWorldExtension> {
  say = "Hello Groovy"
}

//定义插件方式二  引用自定义插件 properties文件名
hsicen {
  name = "黄思程!!!"
}

android {
  defaultConfig {
    namespace = "com.hsicen.a25_gradle"
  }

  buildFeatures {
    buildConfig = true
  }

  //渠道
  flavorDimensions.add("paying")
  productFlavors {
    create("free") {
      dimension = "paying"
      buildConfigField("String", "BaseUrl", "https:www.baidu.com.free")
    }

    create("vip") {
      dimension = "paying"
      buildConfigField("String", "BaseUrl", "https:www.baidu.com.pay")
    }
  }
}

task("bumpVersion") {
  doLast {
    val versionFile = file("./version.properties")
    val props = Properties()
    props.load(FileInputStream(versionFile))
    val codeBumped = props.getProperty("VERSION_CODE", "1").toInt() + 1
    props["VERSION_CODE"] = codeBumped.toString()
    props.store(versionFile.writer(), null)
  }
}

task("notifyVersionBump") {
  dependsOn("bumpVersion")
  doLast {
    println("已经更新了应用版本号！！！")
  }
}



open class Animal(var name: String = "", var legs: Int = 0) {
  override fun toString(): String {
    return "This is animal $name. has $legs legs."
  }
}

open class Dog(var age: Int = 5) : Animal("", 0) {
  override fun toString(): String {
    return "This is dog $name. has $legs legs and $age years old."
  }
}

val animal1 = extensions.create("animal1", Dog::class, 5)
val animal2 = extensions.create(Animal::class, "animal2", Dog::class, 5)
val animal3 = extensions.create(TypeOf.typeOf(Animal::class.java), "animal3", Dog::class.java, 5)

animal1.apply {
  name = "大黄"
  age = 18
  legs = 4
}

animal2.apply {
  name = "二黄"
  legs = 4
}

animal3.apply {
  name = "三黄"
  legs = 4
}

tasks.create("runAnimal") {
  doLast {
    println(animal1.toString())
    println(animal2.toString())
    println(animal3.toString())
  }
}

extensions.add("animal4", Dog::class)
extensions.add(Animal::class, "animal5", Dog(10))
extensions.add(TypeOf.typeOf(Animal::class.java), "animal6", Dog(12))


extensions.findByName("animal1")
extensions.findByType(Animal::class.java)
extensions.findByType(TypeOf.typeOf(Animal::class.java))

extensions.getByName("animal2")
extensions.getByType(Animal::class.java)
extensions.getByType(TypeOf.typeOf(Animal::class.java))

android.applicationVariants
project.android.applicationVariants
(project.property("android") as? com.android.build.gradle.internal.dsl.BaseAppModuleExtension)?.applicationVariants


afterEvaluate {
  android.applicationVariants.all {
    println(mergeResourcesProvider.get().name)
  }
}

project.android.applicationVariants.all {
  outputs.all {
    (this as? com.android.build.gradle.api.ApkVariantOutput)?.let {
      it.outputFileName = "${it.baseName}.apk"
      println(it.outputFileName)
    }
  }
}
