plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.a6_okhttp"
  }
}

dependencies {
  implementation(Deps.okhttp)
  testImplementation(TestDeps.mockWebServer)

  kapt(Deps.moshiKotlinCodegen)
  implementation(Deps.moshiKotlin)
}