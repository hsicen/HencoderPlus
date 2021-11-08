plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.a35_jvm"
  }

  buildTypes {
    create("prd") {
      isMinifyEnabled = false
    }
  }
}