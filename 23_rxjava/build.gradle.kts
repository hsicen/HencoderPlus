plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.hsicen.a23_rxjava"
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

    buildFeatures {
        viewBinding = true
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

    implementation(Deps.retrofit)
    implementation(Deps.converterGson)
    implementation(Deps.adapterRxjava2)

    implementation(Deps.rxjava)
    implementation(Deps.rxandroid)

    //Platform bindings:
    implementation(Deps.rxbinding)
    implementation(Deps.rxbindingCore)
    implementation(Deps.rxbindingAppcompat)
    implementation(Deps.rxbindingDrawerlayout)
    implementation(Deps.rxbindingLeanback)
    implementation(Deps.rxbindingRecyclerview)
    implementation(Deps.rxbindingSlidingpanelayout)
    implementation(Deps.rxbindingSwiperefreshlayout)
    implementation(Deps.rxbindingViewpager)
    implementation(Deps.rxbindingViewpager2)
    implementation(Deps.rxbindingMaterial)
}
