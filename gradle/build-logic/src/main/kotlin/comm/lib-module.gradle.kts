package comm

import Deps
import TestDeps
import Versions

/**
 * 作者：hsicen  11/3/21 10:17
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：android library module comm configuration
 */
plugins {
  id("com.android.library")
  id("kotlin-android")
  id("kotlin-kapt")
}

android {
  compileSdk = Versions.compileSdk

  defaultConfig {
    minSdk = Versions.minSdk
    targetSdk = Versions.targetSdk

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

  kotlinOptions {
    jvmTarget = "17"
  }

  buildFeatures {
    viewBinding = true
  }
}

dependencies {
  testImplementation(TestDeps.junit)
  androidTestImplementation(TestDeps.junitExt)
  androidTestImplementation(TestDeps.espresso)

  // implementation(fileTree(Deps.fileMap))  // 已移除以提升性能
  implementation(Deps.kotlinStb)

  implementation(Deps.coreKtx)
  implementation(Deps.appCompat)
  implementation(Deps.constrainLayout)
}