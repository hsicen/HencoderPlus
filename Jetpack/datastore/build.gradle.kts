plugins {
  id("comm.app-compose-module")
}

android {
  namespace = "com.hsicen.datastore"

  defaultConfig {
    applicationId = "com.hsicen.datastore"
  }
}

dependencies {
  implementation(Deps.dataStoreCore)
  implementation(Deps.dataStoreSp)
  implementation(Deps.dataStorePb)

  // 自动初始化
  implementation(Deps.startUp)
}