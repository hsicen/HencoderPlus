import org.gradle.api.Plugin
import org.gradle.api.Project

class BuildScriptPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    println("It's build script plugin.")

    val buildTask = target.tasks.create("BuildScriptTask") {
      doLast {
        println("Execute build script plugin.")
      }
    }

    // 将 task 挂载到编译流程中
    target.afterEvaluate {
      val mergeTask = target.tasks.findByName("mergeDebugResources")
      mergeTask?.finalizedBy(buildTask)
    }
  }
}