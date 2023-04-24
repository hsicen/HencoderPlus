plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.a30_annotation"
  }
}

dependencies {
  //implementation project(':30_annotation_reflection')
  implementation(project(":hencoder:30_lib"))
  implementation(project(":hencoder:30_lib_annotation"))
  kapt(project(":hencoder:30_lib_processos"))
}