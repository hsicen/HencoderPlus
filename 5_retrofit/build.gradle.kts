plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.a6_retrofit"
  }
}

dependencies {
  implementation(Deps.retrofit)
  implementation(Deps.converterGson)
  implementation(Deps.adapterRxjava2)
}