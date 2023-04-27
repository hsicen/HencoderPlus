buildscript {
  repositories {
    maven(Mavens.aliyunPublic)
    maven(Mavens.aliyunGoogle)
    maven(Mavens.aliyunGooglePlugin)
    maven(Mavens.jitpackIo)

    google()
    mavenCentral()
  }

  dependencies {
    classpath("com.android.tools.build:gradle:${Versions.gradle}")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}")
  }
}

allprojects {
  repositories {
    maven(Mavens.aliyunPublic)
    maven(Mavens.aliyunGoogle)
    maven(Mavens.aliyunGooglePlugin)
    maven(Mavens.jitpackIo)

    google()
    mavenCentral()
  }
}