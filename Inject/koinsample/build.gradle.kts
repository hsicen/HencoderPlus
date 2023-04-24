plugins {
  id("comm.app-module")
}

android {

  defaultConfig {
    namespace = "com.hsicen.koinsample"
  }
}

dependencies {
  implementation(Deps.material)
  implementation(Deps.koinAndroid)
}