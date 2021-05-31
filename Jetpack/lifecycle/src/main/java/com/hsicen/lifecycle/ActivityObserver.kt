package com.hsicen.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * 作者：hsicen  5/31/21 14:47
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class ActivityObserver : Application.ActivityLifecycleCallbacks {
  override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
  }

  override fun onActivityStarted(activity: Activity) {
  }

  override fun onActivityResumed(activity: Activity) {
  }

  override fun onActivityPaused(activity: Activity) {
  }

  override fun onActivityStopped(activity: Activity) {
  }

  override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
  }

  override fun onActivityDestroyed(activity: Activity) {
  }
}