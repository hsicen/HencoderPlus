plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.a24_io"
  }
}

dependencies {
  implementation(Deps.okio)
}