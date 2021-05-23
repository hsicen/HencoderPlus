package com.hsicen.viewbinding

import com.hsicen.extension.extensions.click
import com.hsicen.extension.toast.info
import com.hsicen.viewbinding.databinding.CallFragmentBinding

/**
 * 作者：hsicen  5/23/21 22:48
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class CallFragment : BindingFragment<CallFragmentBinding>() {

  override fun onStart() {
    super.onStart()

    binding.btnCall.click {
      info("Fragment is shout", activity!!)
    }
  }
}