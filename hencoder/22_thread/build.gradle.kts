plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.a22_thread"
  }
}

dependencies {
  implementation(Deps.coroutinesCore)
  implementation(Deps.coroutinesAndroid)
}