package com.hsicen.koinsample

import android.app.Application
import com.hsicen.koinsample.data.User
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * 作者：hsicen  2020/8/16 17:52
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class KoinApp : Application() {
  private val appModule = module {
    single { User(1, "扔物线", "毫无波澜") }
  }

  override fun onCreate() {
    super.onCreate()

    //添加编织图
    startKoin {
      androidLogger()
      androidContext(this@KoinApp)

      modules(appModule)
    }
  }
}
