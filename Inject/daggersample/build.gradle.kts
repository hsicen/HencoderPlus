plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.daggersample"
  }
}

dependencies {
  implementation(Deps.material)

  kapt(Deps.daggerCompiler)
  implementation(Deps.dagger)
}