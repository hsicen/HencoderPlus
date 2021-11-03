plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    applicationId = "com.hsicen.a29_hotfix"
  }

  kotlinOptions {
    jvmTarget = "11"
  }
}

dependencies {
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
