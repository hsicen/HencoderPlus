plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.a28_plugin"
  }
}

dependencies {
  implementation(Deps.okio)
}