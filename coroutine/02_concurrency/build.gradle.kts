plugins {
  id("comm.app-compose-module")
}

android {
  namespace = "com.hsicen.a02_concurrency"

  defaultConfig {
    applicationId = "com.hsicen.a02_concurrency"
  }
}

dependencies {
  implementation(Deps.coroutinesCore)
  implementation(Deps.coroutinesAndroid)

  implementation(Deps.retrofit)
  implementation(Deps.converterGson)
  implementation(Deps.adapterRxjava3)
}
