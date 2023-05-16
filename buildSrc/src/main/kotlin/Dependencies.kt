/**
 * 作者：hsicen  5/6/21 20:38
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

object Versions {
  const val minSdk = 24
  const val compileSdk = 33
  const val targetSdk = 33

  const val versionCode = 1
  const val versionName = "1.0"

  const val kotlin = "1.8.20"
  const val gradle = "8.0.0"
  const val appCompat = "1.3.1"
  const val coreKtx = "1.6.0"
  const val material = "1.4.0"
  const val constraint = "2.1.3"
  const val compose = "1.4.2"
  const val composeCompiler = "1.4.6"

  const val hilt = "2.35"
  const val retrofit = "2.9.0"
  const val coroutine = "1.6.0"
  const val rxBinding = "3.1.0"

  const val room = "2.2.6"
  const val dagger = "2.38.1"
  const val lifecycle = "2.3.1"

  const val dataStore = "1.1.0-alpha04"
}

//依赖库管理
object Deps {
  const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
  val fileMap = mapOf("dir" to "libs", "include" to listOf("*.jar"))
  const val kotlinStb = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
  const val extension = "com.github.hsicen:Extensions:1.1.0"

  const val ktx = "androidx.core:core-ktx:${Versions.coreKtx}"
  const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
  const val material = "com.google.android.material:material:${Versions.material}"
  const val constrainLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"

  const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
  const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
  const val adapterRxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"

  const val okhttp = "com.squareup.okhttp3:okhttp:4.9.0"
  const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.12.0"
  const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:1.12.0"

  const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
  const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"

  const val gson = "com.google.code.gson:gson:2.8.6"
  const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
  const val cardview = "androidx.cardview:cardview:1.0.0"

  const val rxjava = "io.reactivex.rxjava2:rxjava:2.2.10"
  const val rxandroid = "io.reactivex.rxjava2:rxandroid:2.1.1"

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

  const val okio = "com.squareup.okio:okio:2.3.0"
  const val javapoet = "com.squareup:javapoet:1.12.1"
  const val gsonParent = "com.google.code.gson:gson-parent:2.8.6"

  const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
  const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

  const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.dagger}"
  const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.dagger}"

  const val butterknife = "com.jakewharton:butterknife:10.2.3"
  const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
  const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"

  const val koinAndroid = "io.insert-koin:koin-android:3.0.1"

  const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
  const val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle}"
  const val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
  const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:2.2.0"

  const val activityKtx = "androidx.activity:activity-ktx:1.4.0"
  const val fragmentKtx = "androidx.fragment:fragment-ktx:1.4.1"

  const val startUp = "androidx.startup:startup-runtime:1.0.0"

  // 一般依赖 material material-icons-extended ui-tooling 三个就行
  const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
  const val iconExtend = "androidx.compose.material:material-icons-extended:${Versions.compose}"

  const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
  const val composeUiUtil = "androidx.compose.ui:ui-util:${Versions.compose}"
  const val composeUiPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
  const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.compose}"
  const val composeFoundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
  const val lifecycleViewmodelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0"
  const val activityCompose = "androidx.activity:activity-compose:1.3.0-alpha06"
  const val accompanistInsets = "dev.chrisbanes.accompanist:accompanist-insets:0.6.0"
  const val accompanistPager = "com.google.accompanist:accompanist-pager:0.21.4-beta"
  const val coil = "io.coil-kt:coil-compose:2.0.0-alpha05"


  const val dataStoreCore = "androidx.datastore:datastore-core:${Versions.dataStore}"
  const val dataStorePb = "androidx.datastore:datastore:${Versions.dataStore}"
  const val dataStoreSp = "androidx.datastore:datastore-preferences:${Versions.dataStore}"
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