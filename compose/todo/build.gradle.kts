plugins {
  id("comm.app-compose-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.todo"
  }
}

dependencies {
  implementation(Deps.extension)
  implementation(Deps.composeMaterial3)
}