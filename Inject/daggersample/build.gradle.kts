plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.daggersample"
  }
}

dependencies {
  implementation(Deps.material)

  kapt(Deps.daggerCompiler)
  implementation(Deps.dagger)
}