plugins {
    id("comm.app-compose-module")
}

android {
    defaultConfig {
        applicationId = "com.hsicen.a02_sample"
    }
}

dependencies {
    implementation(Deps.accompanistInsets)
    implementation(Deps.accompanistPager)

    implementation(Deps.coil)
}