plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.aicoder.hencoderplus"
  }
}

dependencies {
  implementation(Deps.retrofit)
  implementation(Deps.converterGson)
}
