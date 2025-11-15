buildscript {
  repositories {
    maven("https://maven.aliyun.com/repository/public")
    maven("https://maven.aliyun.com/repository/google")
    maven("https://maven.aliyun.com/repository/gradle-plugin")
    maven("https://jitpack.io")

    google()
    mavenCentral()
    gradlePluginPortal()
  }

  dependencies {
    classpath("com.android.tools.build:gradle:8.6.0")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.20")
    classpath("com.google.dagger:hilt-android-gradle-plugin:2.52")
  }
}

allprojects {
  repositories {
    maven("https://maven.aliyun.com/repository/public")
    maven("https://maven.aliyun.com/repository/google")
    maven("https://maven.aliyun.com/repository/gradle-plugin")
    maven("https://jitpack.io")

    google()
    mavenCentral()
  }
}