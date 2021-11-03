plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.a32_view_draw_process"
  }

  kotlinOptions {
    jvmTarget = "11"
  }
}

dependencies {
  implementation(Deps.gsonParent)
}
