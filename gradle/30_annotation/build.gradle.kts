plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.a30_annotation"
  }
}

dependencies {
  //implementation project(':30_annotation_reflection')
  implementation(project(":30_lib"))
  implementation(project(":30_lib_annotation"))
  kapt(project(":30_lib_processos"))
}