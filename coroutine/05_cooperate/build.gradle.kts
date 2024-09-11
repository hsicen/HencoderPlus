plugins {
    id("comm.app-compose-module")
}

android {
    namespace = "com.hsicen.a05_cooperate"

    defaultConfig {
        applicationId = "com.hsicen.a05_cooperate"
    }
}

dependencies {
    implementation(Deps.coroutinesCore)
    implementation(Deps.coroutinesAndroid)
}
