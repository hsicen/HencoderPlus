import extensions.androidTestImplementation
import extensions.testImplementation

plugins {
  id("comm.app-compose-module")
}

android {
  namespace = "com.hsicen.a01_introduce"

  defaultConfig {
    applicationId = "com.hsicen.a01_introduce"
  }
}

dependencies {
  implementation(Deps.coroutinesCore)
  implementation(Deps.coroutinesAndroid)
}
