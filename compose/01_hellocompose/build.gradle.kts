plugins {
  id("comm.app-compose-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.hellocompose"
  }
}

dependencies {
  implementation(Deps.accompanistInsets)
  implementation(Deps.accompanistPager)
}