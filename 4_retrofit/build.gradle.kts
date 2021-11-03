plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.aicoder.hencoderplus"
  }
}

dependencies {
  implementation(Deps.retrofit)
  implementation(Deps.converterGson)
}
