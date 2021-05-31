package com.hsicen.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * 作者：hsicen  5/31/21 14:24
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：应用生命周期监听
 */
class AppObserver : LifecycleObserver {

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun onResume() {

  }

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  fun onStop() {

  }

}