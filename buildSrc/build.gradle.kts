plugins {
  `kotlin-dsl`
}

repositories {
  maven("https://maven.aliyun.com/repository/public")
  maven("https://maven.aliyun.com/repository/google")
  maven("https://maven.aliyun.com/repository/gradle-plugin")

  google()
  mavenCentral()
}

object PluginVersion {
  const val GRADLE = "8.1.2"
  const val KOTLIN = "1.9.10"
}

dependencies {
  implementation("com.android.tools.build:gradle:${PluginVersion.GRADLE}")
  implementation("com.android.tools.build:gradle-api:${PluginVersion.GRADLE}")
  implementation("org.jetbrains.kotlin:kotlin-stdlib:${PluginVersion.KOTLIN}")
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersion.KOTLIN}")
}
