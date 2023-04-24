plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.viewmodel"
  }
}

dependencies {
  implementation(Deps.material)

  implementation(Deps.lifecycleViewmodel)
  implementation(Deps.activityKtx)
  implementation(Deps.fragmentKtx)
  implementation(Deps.extension)
}