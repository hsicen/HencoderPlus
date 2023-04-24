plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.a36_window_inserts"
  }
}

dependencies {
  implementation(Deps.extension)
}