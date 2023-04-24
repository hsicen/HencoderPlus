plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.a21_drag_nestedscroll"
  }
}

dependencies {
  implementation(Deps.recyclerview)
  implementation(Deps.cardview)
}