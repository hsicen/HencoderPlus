plugins {
  id("comm.app-compose-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.hellocompose"
    minSdk = 24
  }

  packagingOptions {
    resources.pickFirsts.add("META-INF/*")
  }
}

dependencies {
  implementation(Deps.accompanistInsets)
  implementation(Deps.accompanistPager)

  // office 365 sdk
  implementation("com.microsoft.identity.client:msal:2.0.8")
  implementation("com.microsoft.graph:microsoft-graph:3.1.0") {
    exclude("javax.activation")
  }
}