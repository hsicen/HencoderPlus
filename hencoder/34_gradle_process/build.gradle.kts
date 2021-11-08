plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.a34_gradle_process"
  }
}

dependencies {
  compileOnly(Deps.gradle)
}