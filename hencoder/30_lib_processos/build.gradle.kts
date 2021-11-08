plugins {
  id("comm.kotlin-lib")
}

dependencies {
  implementation(project(":hencoder:30_lib_annotation"))
  implementation(Deps.javapoet)
}