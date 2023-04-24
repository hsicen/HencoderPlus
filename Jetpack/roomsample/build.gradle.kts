plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.roomsample"
  }
}

dependencies {
  kapt(Deps.roomCompiler)
  implementation(Deps.roomRuntime)
  implementation(Deps.lifecycleViewmodel)
}