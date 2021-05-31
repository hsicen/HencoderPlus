package com.hsicen.lifecycle

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

/**
 * 作者：hsicen  5/27/21 09:51
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class MyLifecycleOwner : Activity(), LifecycleOwner {
  private lateinit var mLifecycleRegistry: LifecycleRegistry

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mLifecycleRegistry = LifecycleRegistry(this)

    mLifecycleRegistry.currentState = Lifecycle.State.CREATED
  }

  override fun onStart() {
    super.onStart()
    mLifecycleRegistry.currentState = Lifecycle.State.STARTED
  }

  override fun onResume() {
    super.onResume()
    mLifecycleRegistry.currentState = Lifecycle.State.RESUMED
    mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
  }

  override fun onDestroy() {
    super.onDestroy()
    mLifecycleRegistry.currentState = Lifecycle.State.DESTROYED
  }

  override fun getLifecycle(): Lifecycle {
    return mLifecycleRegistry
  }

}