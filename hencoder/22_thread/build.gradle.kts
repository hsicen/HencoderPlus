plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.a22_thread"
  }
}

dependencies {
  implementation(Deps.coroutinesCore)
  implementation(Deps.coroutinesAndroid)
}