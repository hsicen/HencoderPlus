plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.hsicen.a30_annotation"
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

    //implementation project(':30_annotation_reflection')
    implementation(project(":30_lib"))
    implementation(project(":30_lib_annotation"))
    kapt(project(":30_lib_processos"))
}
