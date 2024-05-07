plugins {
  id("comm.app-compose-module")
}

android {
  namespace = "com.hsicen.a08_custom"

  defaultConfig {
    applicationId = "com.hsicen.a08_custom"
  }
}

dependencies {
  implementation(Deps.extension)
}
