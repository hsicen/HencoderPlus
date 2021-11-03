plugins {
  id("comm.app-module")
}

android {

  defaultConfig {
    applicationId = "com.hsicen.a28_plugin_lib"
    minSdk = Versions.minSdk
    targetSdk = Versions.targetSdk
    versionCode = Versions.versionCode
    versionName = Versions.versionName

    testInstrumentationRunner = TestDeps.runner
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
  }
}

dependencies {
  testImplementation(TestDeps.junit)
  androidTestImplementation(TestDeps.junitExt)
  androidTestImplementation(TestDeps.espresso)

  implementation(fileTree(Deps.fileMap))
  implementation(Deps.kotlinStb)

  implementation(Deps.appCompat)
  implementation(Deps.ktx)
  implementation(Deps.constrainLayout)
}
