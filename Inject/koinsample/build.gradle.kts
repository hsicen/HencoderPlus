plugins {
  id("comm.app-module")
}

android {

  defaultConfig {
    applicationId = "com.hsicen.koinsample"
  }
}

dependencies {
  implementation(Deps.material)
  implementation(Deps.koinAndroid)
}