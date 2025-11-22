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
    val GRADLE = "8.13.1"
    val KOTLIN = "2.2.21"
    val HILT = "2.57.2"

    classpath("com.android.tools.build:gradle:$GRADLE")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN")
    classpath("com.google.dagger:hilt-android-gradle-plugin:$HILT")
  }
}