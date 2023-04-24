plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.lifecycle"
  }
}

dependencies {
  implementation(Deps.material)
  implementation(Deps.lifecycleExtensions)
}