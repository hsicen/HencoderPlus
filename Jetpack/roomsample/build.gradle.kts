plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.roomsample"
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  kapt(Deps.roomCompiler)
  implementation(Deps.roomRuntime)
  implementation(Deps.lifecycleViewmodel)
}