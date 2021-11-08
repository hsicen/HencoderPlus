plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.a28_plugin"
  }
}

dependencies {
  implementation(Deps.okio)
}