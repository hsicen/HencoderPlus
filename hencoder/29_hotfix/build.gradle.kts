import org.gradle.process.ExecOperations
import javax.inject.Inject

plugins {
  id("comm.app-module")
}

android {
  defaultConfig {
    namespace = "com.hsicen.a29_hotfix"
  }
}

dependencies {
  implementation(Deps.okio)
}

//需要改动文件的路径
val patchPath = "com/hsicen/a29_hotfix/Title"

abstract class HotfixTask @Inject constructor(private val execOps: ExecOperations) : DefaultTask() {
  @get:Input
  abstract val patchPath: Property<String>

  @TaskAction
  fun run() {
    val path = patchPath.get()
    execOps.exec { commandLine("rm", "-r", "./build/patch") }
    execOps.exec { commandLine("mkdir", "./build/patch") }
    execOps.exec { commandLine("javac", "./src/main/java/${path}.java", "-d", "./build/patch") }
    execOps.exec {
      commandLine(
        "C:\\Android\\SDK\\build-tools\\29.0.3\\d8",
        "./build/patch/${path}.class",
        "--output",
        "./build/patch"
      )
    }
    execOps.exec { commandLine("mv", "./build/patch/classes.dex", "./build/patch/hotfix.dex") }
  }
}

tasks.register<HotfixTask>("hotfix") {
  patchPath.set("com/hsicen/a29_hotfix/Title")
}
