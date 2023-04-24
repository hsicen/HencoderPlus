plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.appstartup"
  }
}

dependencies {
  implementation(Deps.material)
  implementation(Deps.startUp)
}