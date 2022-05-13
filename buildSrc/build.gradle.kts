plugins {
  `kotlin-dsl`
}

repositories {
  google()
  mavenCentral()
}

object PluginVersion {
  const val GRADLE = "7.1.2"
  const val KOTLIN = "1.6.10"
}

dependencies {
  implementation("com.android.tools.build:gradle:${PluginVersion.GRADLE}")
  implementation("com.android.tools.build:gradle-api:${PluginVersion.GRADLE}")
  implementation("org.jetbrains.kotlin:kotlin-stdlib:${PluginVersion.KOTLIN}")
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersion.KOTLIN}")
}
