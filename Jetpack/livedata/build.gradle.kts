plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.livedata"
  }
}

dependencies {
  implementation(Deps.material)
  implementation(Deps.extension)
  implementation(Deps.lifecycleViewmodel)
}