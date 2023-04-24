plugins {
  id("comm.app-module")
}

android {

  defaultConfig {
    namespace = "com.hsicen.a23_rxjava"
  }
}

dependencies {
  implementation(Deps.retrofit)
  implementation(Deps.converterGson)
  implementation(Deps.adapterRxjava2)

  implementation(Deps.rxjava)
  implementation(Deps.rxandroid)

  //Platform bindings:
  implementation(Deps.rxbinding)
  implementation(Deps.rxbindingCore)
  implementation(Deps.rxbindingAppcompat)
  implementation(Deps.rxbindingDrawerlayout)
  implementation(Deps.rxbindingLeanback)
  implementation(Deps.rxbindingRecyclerview)
  implementation(Deps.rxbindingSlidingpanelayout)
  implementation(Deps.rxbindingSwiperefreshlayout)
  implementation(Deps.rxbindingViewpager)
  implementation(Deps.rxbindingViewpager2)
  implementation(Deps.rxbindingMaterial)
}
