plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.lifecycle"
  }
}

dependencies {
  implementation(Deps.material)
  implementation(Deps.lifecycleExtensions)
}