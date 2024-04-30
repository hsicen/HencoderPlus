plugins {
    id("comm.app-compose-module")
}

android {
    namespace = "com.hsicen.a07_view"

    defaultConfig {
        applicationId = "com.hsicen.a07_view"
    }
}

dependencies {
    implementation(Deps.liveData)
    implementation(Deps.coil)
}
