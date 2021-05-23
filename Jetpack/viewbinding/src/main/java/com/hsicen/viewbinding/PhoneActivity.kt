package com.hsicen.viewbinding

import android.os.Bundle
import com.hsicen.extension.extensions.clickThrottle
import com.hsicen.viewbinding.databinding.PhoneActivityBinding

/**
 * 作者：hsicen  5/23/21 22:10
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class PhoneActivity : BindingActivity<PhoneActivityBinding>() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding.btnPhone.clickThrottle {
      finish()
    }
  }

}