plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.daggersample"
  }

  kotlinOptions {
    jvmTarget = "11"
  }
}

dependencies {
  implementation(Deps.material)

  kapt(Deps.daggerCompiler)
  implementation(Deps.dagger)
}