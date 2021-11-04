plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.roomsample"
  }
}

dependencies {
  kapt(Deps.roomCompiler)
  implementation(Deps.roomRuntime)
  implementation(Deps.lifecycleViewmodel)
}