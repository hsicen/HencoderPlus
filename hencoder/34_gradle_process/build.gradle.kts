plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.a34_gradle_process"
  }
}

dependencies {
  compileOnly(Deps.gradle)
}