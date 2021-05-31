package com.hsicen.lifecycle

import android.app.Application
import android.content.Context
import androidx.lifecycle.ProcessLifecycleOwner

/**
 * 作者：hsicen  5/31/21 14:26
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class App : Application() {

  override fun attachBaseContext(base: Context?) {
    super.attachBaseContext(base)

  }

  override fun onCreate() {
    super.onCreate()

    //应用生命周期监听
    ProcessLifecycleOwner.get().lifecycle.addObserver(AppObserver())

    //Activity生命周期监听
    registerActivityLifecycleCallbacks(ActivityObserver())
  }
}