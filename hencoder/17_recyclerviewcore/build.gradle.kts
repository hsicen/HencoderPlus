plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.recyclerviewcore"
  }
}

dependencies {
  implementation(Deps.material)
  implementation(Deps.recyclerview)
}