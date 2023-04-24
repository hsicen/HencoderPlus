plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.a35_jvm"
  }

  buildTypes {
    create("prd") {
      isMinifyEnabled = false
    }
  }
}