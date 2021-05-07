plugins {
    id("kotlin")
    id("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(fileTree(Deps.fileMap))
    implementation(Deps.kotlinStb)
}