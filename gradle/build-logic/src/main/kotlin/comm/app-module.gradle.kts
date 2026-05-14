package comm

import com.android.build.api.dsl.ApplicationExtension
import Deps
import TestDeps
import Versions

/**
 * 作者：hsicen  11/3/21 10:17
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：android application module comm configuration
 */
plugins {
  id("com.android.application")
}

extensions.configure<ApplicationExtension>("android") {
  compileSdk = Versions.compileSdk

  defaultConfig {
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

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  buildFeatures.viewBinding = true
}

dependencies {
  testImplementation(TestDeps.junit)
  androidTestImplementation(TestDeps.junitExt)
  androidTestImplementation(TestDeps.espresso)

  implementation(Deps.kotlinStb)
  implementation(Deps.coreKtx)
  implementation(Deps.appCompat)
  implementation(Deps.constrainLayout)
}
