plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.appstartup"
  }
}

dependencies {
  implementation(Deps.material)
  implementation(Deps.startUp)
}