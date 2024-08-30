/**
 * 作者：hsicen  5/6/21 20:38
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

object Versions {
  const val minSdk = 26
  const val compileSdk = 34
  const val targetSdk = 34

  const val versionCode = 1
  const val versionName = "1.0"

  const val kotlin = "2.0.20"
  const val gradle = "8.5.2"

  const val appCompat = "1.6.1"
  const val material = "1.4.0"
  const val constraint = "2.1.4"
  const val cardview = "1.0.0"
  const val rv = "1.3.2"

  const val compose = "1.6.8"
  const val composeMd3 = "1.2.1"
  const val composeCompiler = "1.5.14"
  const val composeActivity = "1.9.0"
  const val composeViewModel = "2.4.0"
  const val composeCoil = "2.6.0"
  const val accompanistInsets = "0.30.1"
  const val accompanistPager = "0.34.0"

  const val hilt = "2.35"
  const val room = "2.2.6"
  const val dagger = "2.38.1"
  const val lifecycle = "2.8.0"
  const val dataStore = "1.1.1"
  const val koin = "3.4.0"
  const val startup = "1.1.1"
  const val lifecycleExt = "2.2.0"
  const val coreKtx = "1.13.1"
  const val activityKtx = "1.9.0"
  const val fragmentKtx = "1.7.1"

  const val retrofit = "2.11.0"
  const val coroutine = "1.8.1"
  const val okhttp = "4.9.0"
  const val moshi = "1.12.0"
  const val gson = "2.8.6"
  const val rxJava = "2.2.10"
  const val rxAndroid = "2.1.1"
  const val okio = "2.3.0"
  const val javapoet = "1.12.1"
  const val rxBinding = "3.1.0"
  const val butterKnife = "10.2.3"
}

//依赖库管理
object Deps {
  const val plugGradle = "com.android.tools.build:gradle:${Versions.gradle}"
  const val plugKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
  const val plugHilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

  val fileMap = mapOf("dir" to "libs", "include" to listOf("*.jar"))
  const val kotlinStb = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
  const val extension = "com.github.hsicen:Extensions:1.1.0"

  const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
  const val material = "com.google.android.material:material:${Versions.material}"
  const val constrainLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
  const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.rv}"
  const val cardview = "androidx.cardview:cardview:${Versions.cardview}"

  const val gson = "com.google.code.gson:gson:${Versions.gson}"
  const val gsonParent = "com.google.code.gson:gson-parent:${Versions.gson}"
  const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
  const val okio = "com.squareup.okio:okio:${Versions.okio}"
  const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
  const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
  const val javapoet = "com.squareup:javapoet:${Versions.javapoet}"

  const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
  const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
  const val adapterRxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
  const val adapterRxjava3 = "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofit}"

  const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
  const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"

  const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
  const val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

  const val rxbinding = "com.jakewharton.rxbinding3:rxbinding:${Versions.rxBinding}"
  const val rxbindingCore = "com.jakewharton.rxbinding3:rxbinding-core:${Versions.rxBinding}"
  const val rxbindingAppcompat = "com.jakewharton.rxbinding3:rxbinding-appcompat:${Versions.rxBinding}"
  const val rxbindingDrawerlayout = "com.jakewharton.rxbinding3:rxbinding-drawerlayout:${Versions.rxBinding}"
  const val rxbindingLeanback = "com.jakewharton.rxbinding3:rxbinding-leanback:${Versions.rxBinding}"
  const val rxbindingRecyclerview = "com.jakewharton.rxbinding3:rxbinding-recyclerview:${Versions.rxBinding}"
  const val rxbindingSlidingpanelayout = "com.jakewharton.rxbinding3:rxbinding-slidingpanelayout:${Versions.rxBinding}"
  const val rxbindingSwiperefreshlayout = "com.jakewharton.rxbinding3:rxbinding-swiperefreshlayout:${Versions.rxBinding}"
  const val rxbindingViewpager = "com.jakewharton.rxbinding3:rxbinding-viewpager:${Versions.rxBinding}"
  const val rxbindingViewpager2 = "com.jakewharton.rxbinding3:rxbinding-viewpager2:${Versions.rxBinding}"
  const val rxbindingMaterial = "com.jakewharton.rxbinding3:rxbinding-material:${Versions.rxBinding}"
  const val butterknife = "com.jakewharton:butterknife:${Versions.butterKnife}"

  const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
  const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

  const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.dagger}"
  const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.dagger}"

  const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
  const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"

  const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"

  const val startUp = "androidx.startup:startup-runtime:${Versions.startup}"

  const val liveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"

  const val dataStoreCore = "androidx.datastore:datastore-core:${Versions.dataStore}"
  const val dataStorePb = "androidx.datastore:datastore:${Versions.dataStore}"
  const val dataStoreSp = "androidx.datastore:datastore-preferences:${Versions.dataStore}"

  const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
  const val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle}"
  const val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
  const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExt}"

  const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
  const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
  const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"

  // 一般依赖 material(3) material-icons-extended ui-tooling 三个就行
  const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
  const val iconExtend = "androidx.compose.material:material-icons-extended:${Versions.compose}"
  const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.composeMd3}"
  const val material3Window = "androidx.compose.material3:material3-window-size-class:${Versions.composeMd3}"

  const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
  const val composeUiUtil = "androidx.compose.ui:ui-util:${Versions.compose}"
  const val composeUiPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
  const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.compose}"
  const val composeFoundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
  const val lifecycleViewmodelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModel}"
  const val activityCompose = "androidx.activity:activity-compose:${Versions.composeActivity}"
  const val accompanistInsets = "com.google.accompanist:accompanist-insets:${Versions.accompanistInsets}"
  const val accompanistPager = "com.google.accompanist:accompanist-pager:${Versions.accompanistPager}"
  const val coil = "io.coil-kt:coil-compose:${Versions.composeCoil}"
}

object TestDeps {
  const val runner = "androidx.test.runner.AndroidJUnitRunner"

  const val junit = "junit:junit:4.13.1"
  const val junitExt = "androidx.test.ext:junit:1.1.2"
  const val espresso = "androidx.test.espresso:espresso-core:3.3.0"

  const val mockWebServer = "com.squareup.okhttp3:mockwebserver:4.4.1"

  const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
  const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
}

object Mavens {
  const val jitpackIo = "https://jitpack.io"
  const val aliyunPublic = "https://maven.aliyun.com/repository/public" // central & jcenter
  const val aliyunGoogle = "https://maven.aliyun.com/repository/google"
  const val aliyunGooglePlugin = "https://maven.aliyun.com/repository/gradle-plugin"
}