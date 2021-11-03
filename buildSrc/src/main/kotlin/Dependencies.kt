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

  const val kotlin = "1.5.31"
  const val gradle = "7.0.3"
  const val compose = "1.0.1"

  const val hilt = "2.35"
  const val retrofit = "2.9.0"
  const val coroutine = "1.4.2"
  const val rxBinding = "3.1.0"

  const val room = "2.2.6"
  const val dagger = "2.35"
  const val lifecycle = "2.3.1"
}

//依赖库管理
object Deps {
  const val gradle = "com.android.tools.build:gradle:7.0.3"
  val fileMap = mapOf("dir" to "libs", "include" to listOf("*.jar"))
  const val kotlinStb = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
  const val extension = "com.github.hsicen:Extensions:1.0.1"

  const val ktx = "androidx.core:core-ktx:1.3.2"
  const val appCompat = "androidx.appcompat:appcompat:1.2.0"
  const val material = "com.google.android.material:material:1.2.1"
  const val constrainLayout = "androidx.constraintlayout:constraintlayout:2.0.4"

  const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
  const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
  const val adapterRxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"

  const val okhttp = "com.squareup.okhttp3:okhttp:4.9.0"
  const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.12.0"
  const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:1.12.0"

  const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
  const val coroutinesAndroid =
    "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"

  const val gson = "com.google.code.gson:gson:2.8.6"
  const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
  const val cardview = "androidx.cardview:cardview:1.0.0"

  const val rxjava = "io.reactivex.rxjava2:rxjava:2.2.10"
  const val rxandroid = "io.reactivex.rxjava2:rxandroid:2.1.1"

  const val rxbinding = "com.jakewharton.rxbinding3:rxbinding:${Versions.rxBinding}"
  const val rxbindingCore = "com.jakewharton.rxbinding3:rxbinding-core:${Versions.rxBinding}"
  const val rxbindingAppcompat =
    "com.jakewharton.rxbinding3:rxbinding-appcompat:${Versions.rxBinding}"
  const val rxbindingDrawerlayout =
    "com.jakewharton.rxbinding3:rxbinding-drawerlayout:${Versions.rxBinding}"
  const val rxbindingLeanback =
    "com.jakewharton.rxbinding3:rxbinding-leanback:${Versions.rxBinding}"
  const val rxbindingRecyclerview =
    "com.jakewharton.rxbinding3:rxbinding-recyclerview:${Versions.rxBinding}"
  const val rxbindingSlidingpanelayout =
    "com.jakewharton.rxbinding3:rxbinding-slidingpanelayout:${Versions.rxBinding}"
  const val rxbindingSwiperefreshlayout =
    "com.jakewharton.rxbinding3:rxbinding-swiperefreshlayout:${Versions.rxBinding}"
  const val rxbindingViewpager =
    "com.jakewharton.rxbinding3:rxbinding-viewpager:${Versions.rxBinding}"
  const val rxbindingViewpager2 =
    "com.jakewharton.rxbinding3:rxbinding-viewpager2:${Versions.rxBinding}"
  const val rxbindingMaterial =
    "com.jakewharton.rxbinding3:rxbinding-material:${Versions.rxBinding}"

  const val okio = "com.squareup.okio:okio:2.3.0"
  const val javapoet = "com.squareup:javapoet:1.12.1"
  const val gsonParent = "com.google.code.gson:gson-parent:2.8.6"

  const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
  const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

  const val hiltCompiler = "androidx.hilt:hilt-compiler:1.0.0"
  const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.dagger}"
  const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.dagger}"

  const val butterknife = "com.jakewharton:butterknife:10.2.3"
  const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
  const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"

  const val koinAndroid = "io.insert-koin:koin-android:3.0.1"

  const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
  const val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle}"
  const val lifecycleViewmodelKtx =
    "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
  const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:2.2.0"

  const val activityKtx = "androidx.activity:activity-ktx:1.2.3"
  const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.3"

  const val startUp = "androidx.startup:startup-runtime:1.0.0"

  val appLibs = arrayListOf<String>().apply {
    add(appCompat)
    add(kotlinStb)
    add(ktx)
    add(constrainLayout)
  }
}

object TestDeps {
  const val runner = "androidx.test.runner.AndroidJUnitRunner"

  const val junit = "junit:junit:4.13.1"
  const val junitExt = "androidx.test.ext:junit:1.1.2"
  const val espresso = "androidx.test.espresso:espresso-core:3.3.0"

  const val mockWebServer = "com.squareup.okhttp3:mockwebserver:4.4.1"

  val androidTestLibs = arrayListOf<String>().apply {
    add(junitExt)
    add(espresso)
  }

  val testLibs = arrayListOf<String>().apply {
    add(junit)
  }
}
