import java.io.FileInputStream
import java.util.*

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
    applicationId = "com.hsicen.a25_gradle"
  }

  //渠道
  flavorDimensions.add("paying")
  productFlavors {
    create("free") {
      dimension = "paying"
      buildConfigField("String", "BaseUrl", "https:www.baidu.com.free")
    }

    create("pay") {
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