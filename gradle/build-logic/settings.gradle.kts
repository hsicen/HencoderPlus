rootProject.name = "build-logic"

dependencyResolutionManagement {
  repositories {
    maven("https://jitpack.io")
    maven("https://maven.aliyun.com/repository/public")
    maven("https://maven.aliyun.com/repository/google")
    maven("https://maven.aliyun.com/repository/gradle-plugin")

    google()
    mavenCentral()
  }
}

