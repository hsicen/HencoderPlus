plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.kolitn"
  }
}

dependencies {
  implementation(Deps.gson)
  implementation(Deps.coroutinesCore)
  implementation(Deps.coroutinesAndroid)
}