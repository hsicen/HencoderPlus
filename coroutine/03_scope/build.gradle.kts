plugins {
  id("comm.app-compose-module")
}

android {
  namespace = "com.hsicen.a03_scope"

  defaultConfig {
    applicationId = "com.hsicen.a03_scope"
  }
}

dependencies {
  implementation(Deps.coroutinesCore)
  implementation(Deps.coroutinesAndroid)
}
