plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.kolitn"
  }
}

dependencies {
  implementation(Deps.gson)
  implementation(Deps.coroutinesCore)
  implementation(Deps.coroutinesAndroid)
}