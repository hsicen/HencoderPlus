plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.a24_io"
  }
}

dependencies {
  implementation(Deps.okio)
}