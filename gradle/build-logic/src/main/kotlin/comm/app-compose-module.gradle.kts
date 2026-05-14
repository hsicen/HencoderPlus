package comm

import com.android.build.api.dsl.ApplicationExtension
import Deps
import TestDeps
import Versions

/**
 * 作者：hsicen  11/3/21 10:17
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：android application module comm configuration with compose
 */
plugins {
  id("com.android.application")
  kotlin("plugin.compose")
}

extensions.configure<ApplicationExtension>("android") {
  compileSdk = Versions.compileSdk

  defaultConfig {
    minSdk = Versions.minSdk
    targetSdk = Versions.targetSdk
    versionCode = Versions.versionCode
    versionName = Versions.versionName
    testInstrumentationRunner = TestDeps.runner
    vectorDrawables {
      useSupportLibrary = true
    }
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

  buildFeatures.compose = true

  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  testImplementation(TestDeps.junit)
  androidTestImplementation(TestDeps.junitExt)
  androidTestImplementation(TestDeps.espresso)

  implementation(Deps.coreKtx)
  implementation(Deps.appCompat)
  implementation(Deps.material)
  implementation(Deps.activityKtx)
  implementation(Deps.activityCompose)
  implementation(Deps.lifecycleRuntimeKtx)
  implementation(Deps.lifecycleViewmodelKtx)
  implementation(Deps.lifecycleViewmodelCompose)
  implementation(Deps.composeMaterial)
  implementation(Deps.composeMaterial3)
  androidTestImplementation(TestDeps.composeUiTest)
  debugImplementation(TestDeps.composeUiTooling)
}
