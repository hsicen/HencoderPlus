plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.hsicen.a29_hotfix"
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

    implementation(Deps.okio)
}

//需要改动文件的路径
val patchPath = "com/hsicen/a29_hotfix/Title"

task("hotfix") {
    doLast {
        exec { commandLine("rm", "-r", "./build/patch") }
        exec { commandLine("mkdir", "./build/patch") }
        exec { commandLine("javac", "./src/main/java/${patchPath}.java", "-d", "./build/patch") }
        exec {
            commandLine(
                "C:\\Android\\SDK\\build-tools\\29.0.3\\d8",
                "./build/patch/${patchPath}.class",
                "--output",
                "./build/patch"
            )
        }
        exec { commandLine("mv", "./build/patch/classes.dex", "./build/patch/hotfix.dex") }
    }
}
