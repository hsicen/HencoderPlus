plugins {
  id("comm.app-module")
  id("com.google.devtools.ksp")
}

android {
  defaultConfig {
    namespace = "com.hsicen.daggersample"
  }
}

dependencies {
  implementation(Deps.material)

  ksp(Deps.daggerCompiler)
  implementation(Deps.dagger)
}
