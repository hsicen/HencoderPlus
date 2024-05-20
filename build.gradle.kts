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
    classpath(Deps.plugGradle)
    classpath(Deps.plugKotlin)
    classpath(Deps.plugHilt)
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