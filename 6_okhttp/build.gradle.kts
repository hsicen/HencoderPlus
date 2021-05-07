plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.hsicen.a6_okhttp"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName
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
    implementation(fileTree(Deps.fileMap))
    implementation(Deps.kotlinStb)

    implementation(Deps.appCompat)
    implementation(Deps.ktx)
    implementation(Deps.constrainLayout)

    implementation(Deps.okhttp)
    testImplementation(TestDeps.mockWebServer)

    kapt(Deps.moshiKotlinCodegen)
    implementation(Deps.moshiKotlin)
}
