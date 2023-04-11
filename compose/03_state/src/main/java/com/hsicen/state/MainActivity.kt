package com.hsicen.state

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 作者：hsicen  12/20/21 22:19
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：State
 */
class MainActivity : AppCompatActivity() {
  companion object {
    const val TAG = "hsc"
  }

  private val hsicen: String by NameDelegate()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    compositionLocal1704()
  }
}