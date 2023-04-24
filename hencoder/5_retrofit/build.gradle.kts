plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.a6_retrofit"
  }
}

dependencies {
  implementation(Deps.coroutinesCore)

  implementation(Deps.retrofit)
  implementation(Deps.converterGson)
  implementation(Deps.adapterRxjava2)
}