plugins {
  id("comm.app-module")
  id("dagger.hilt.android.plugin")
}

android {
  defaultConfig {
    applicationId = "com.android.hsicen.hiltsample"
  }
}

dependencies {
  //Hilt 依赖
  kapt(Deps.hiltAndroidCompiler)
  implementation(Deps.hiltAndroid)

  //ButterKnife
  implementation(Deps.butterknife)
}

kapt {
  correctErrorTypes = true
}