plugins {
  id("comm.app-module")
  id("com.google.devtools.ksp")
}

android {
  defaultConfig {
    namespace = "com.hsicen.a6_okhttp"
  }
}

dependencies {
  implementation(Deps.okhttp)
  testImplementation(TestDeps.mockWebServer)

  ksp(Deps.moshiKotlinCodegen)
  implementation(Deps.moshiKotlin)
}