plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.hsicen.a6_retrofit"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
}
