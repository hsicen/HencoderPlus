plugins {
  id("comm.app-module")
  id("com.google.devtools.ksp")
}

android {
  defaultConfig {
    namespace = "com.hsicen.a30_annotation"
  }
}

dependencies {
  implementation(project(":hencoder:30_lib"))
  implementation(project(":hencoder:30_lib_annotation"))
  ksp(project(":hencoder:30_lib_processos"))
}
