plugins {
  id("comm.app-module")
}

android {

  defaultConfig {
    applicationId = "com.hsicen.a21_drag_nestedscroll"
  }
  kotlinOptions {
    jvmTarget = "11"
  }
}

dependencies {
  implementation(Deps.recyclerview)
  implementation(Deps.cardview)
}