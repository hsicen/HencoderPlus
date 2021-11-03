plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.appstartup"
  }

  kotlinOptions {
    jvmTarget = "11"
  }
}

dependencies {
  implementation(Deps.material)
  implementation(Deps.startUp)
}