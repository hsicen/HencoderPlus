plugins {
  id("comm.kotlin-lib")
}

dependencies {
  implementation(project(":30_lib_annotation"))
  implementation(Deps.javapoet)
}