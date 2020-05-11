package com.hsicen.touch.sample

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton

/**
 * 作者：hsicen  2020/5/8 17:56
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class SampleButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {
    private var mClick = 0

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val touch = ++mClick < 10
        val onTouchEvent = super.onTouchEvent(event)
        Log.d("hsc", "$onTouchEvent  Button onTouchEvent: $event")
        return false
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.d("hsc", "Button dispatchTouchEvent: $event")

        return super.dispatchTouchEvent(event)
    }

}