/**
 * 作者：hsicen  5/6/21 20:38
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

object Versions {
    const val minSdk = 21
    const val compileSdk = 30
    const val targetSdk = 30

    const val versionCode = 1
    const val versionName = "1.0"

    const val kotlin = "1.5.0"
    const val gradle = "4.2.0"
    const val hilt = "2.35"

    const val retrofit = "2.9.0"
    const val coroutine = "1.4.2"
}

//依赖库管理
object Deps {
    val fileMap = mapOf("dir" to "libs", "include" to listOf("*.jar"))
    const val kotlinStb = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val ktx = "androidx.core:core-ktx:1.3.2"
    const val appCompat = "androidx.appcompat:appcompat:1.2.0"
    const val material = "com.google.android.material:material:1.2.1"
    const val constrainLayout = "androidx.constraintlayout:constraintlayout:2.0.4"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val adapterRxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"

    const val okhttp = "com.squareup.okhttp3:okhttp:4.9.0"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.9.2"
    const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:1.11.02"

    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"

    const val gson = "com.google.code.gson:gson:2.8.6"
}

object TestDeps {
    const val junit = "junit:junit:4.13.1"
    const val junitExt = "androidx.test.ext:junit:1.1.2"
    const val espresso = "androidx.test.espresso:espresso-core:3.3.0"

    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:4.4.1"
}

object Plugin {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:2.35"
}

