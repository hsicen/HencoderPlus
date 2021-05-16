package com.hsicen.touch

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 * 作者：hsicen  2020/5/8 17:36
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class CaptureView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

  override fun onTouchEvent(event: MotionEvent?): Boolean {
    val actionMasked = event?.actionMasked
    Log.d("hsc", "Event: $event")

    return true
  }
}