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
    const val buildTools = "30.0.3"

    const val versionCode = 1
    const val versionName = "1.0"

    const val kotlin = "1.5.0"
    const val gradle = "4.2.0"
}


//依赖库管理
object Deps {
    const val kotlinStb = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val ktx = "androidx.core:core-ktx:1.3.2"
    const val appCompat = "androidx.appcompat:appcompat:1.2.0"
    const val material = "com.google.android.material:material:1.2.1"
}

object Plugin {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:2.35"

}

