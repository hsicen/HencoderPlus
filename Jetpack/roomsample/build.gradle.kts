plugins {
  id("comm.app-module")
  id("com.google.devtools.ksp")
}

android {
  defaultConfig {
    namespace = "com.hsicen.roomsample"
  }
}

dependencies {
  ksp(Deps.roomCompiler)
  implementation(Deps.roomRuntime)
  implementation(Deps.lifecycleViewmodel)
}