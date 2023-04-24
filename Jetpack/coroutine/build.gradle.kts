plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.coroutine"
  }
}

dependencies {
  implementation(Deps.material)

  implementation(Deps.coroutinesCore)
  implementation(Deps.coroutinesAndroid)

  implementation(Deps.lifecycleRuntimeKtx)
  implementation(Deps.lifecycleViewmodelKtx)
}