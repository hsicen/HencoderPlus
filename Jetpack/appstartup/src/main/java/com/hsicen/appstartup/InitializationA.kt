package com.hsicen.appstartup

import android.content.Context
import androidx.startup.Initializer

/**
 * 作者：hsicen  5/31/21 16:16
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class InitializationA : Initializer<AppContext> {
  override fun create(context: Context): AppContext {
    AppContext.inject(context)

    return AppContext
  }

  override fun dependencies(): MutableList<Class<out Initializer<*>>> {
    return mutableListOf()
  }
}