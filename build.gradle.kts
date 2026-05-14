buildscript {
  repositories {
    maven("https://maven.aliyun.com/repository/public")
    maven("https://maven.aliyun.com/repository/google")
    maven("https://maven.aliyun.com/repository/gradle-plugin")

    google()
    mavenCentral()
    gradlePluginPortal()
  }

  dependencies {
    val gradle = "9.2.1"
    val kotlin = "2.3.21"
    val hilt = "2.59.2"

    classpath("com.android.tools.build:gradle:$gradle")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin")
    classpath("com.google.dagger:hilt-android-gradle-plugin:$hilt")
  }
}