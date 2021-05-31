package com.hsicen.appstartup

import android.app.Application
import android.content.Context
import android.util.Log

/**
 * 作者：hsicen  5/31/21 15:54
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class App : Application() {

  override fun attachBaseContext(base: Context?) {
    Log.d("hsc", "App before attachBaseContext")
    super.attachBaseContext(base)
    Log.d("hsc", "App after attachBaseContext")
  }

  override fun onCreate() {
    Log.d("hsc", "App before create")
    super.onCreate()
    Log.d("hsc", "App after create")
  }
}