plugins {
  id("comm.app-module")
  id("com.google.devtools.ksp")
  id("dagger.hilt.android.plugin")
}

android {
  defaultConfig {
    namespace = "com.android.hsicen.hiltsample"
  }
}

dependencies {
  ksp(Deps.hiltAndroidCompiler)
  implementation(Deps.hiltAndroid)

  implementation(Deps.butterknife)
}