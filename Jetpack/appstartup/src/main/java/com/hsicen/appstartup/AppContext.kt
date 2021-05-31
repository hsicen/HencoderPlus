package com.hsicen.appstartup

import android.annotation.SuppressLint
import android.content.Context

/**
 * 作者：hsicen  5/31/21 16:19
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
@SuppressLint("StaticFieldLeak")
object AppContext {
  lateinit var mContext: Context

  fun inject(app: Context) {
    mContext = app
  }

}