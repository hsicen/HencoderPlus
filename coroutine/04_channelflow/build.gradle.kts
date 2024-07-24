plugins {
    id("comm.app-compose-module")
}

android {
    namespace = "com.hsicen.a04_channelflow"

    defaultConfig {
        applicationId = "com.hsicen.a04_channelflow"
    }
}

dependencies {
    implementation(Deps.coroutinesCore)
    implementation(Deps.coroutinesAndroid)
}
