plugins {
  id("comm.app-compose-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.hellocompose"
  }
}

dependencies {
  implementation(Deps.accompanistInsets)
}