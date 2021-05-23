package com.hsicen.viewbinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.extension.extensions.click
import com.hsicen.extension.extensions.toActivity
import com.hsicen.viewbinding.databinding.ActivityMainBinding

/**
 * 作者：hsicen  5/23/21 18:05
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class MainActivity : AppCompatActivity() {
  private val mainBinding by inflate<ActivityMainBinding>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    mainBinding.button.text = "Hello Kotlin"

    mainBinding.button.click {
      toActivity<PhoneActivity>(this) {}
    }
  }
}