package com.hsicen.touch.sample

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup

/**
 * 作者：hsicen  2020/5/8 18:00
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class SampleViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val child = getChildAt(0)
        child.layout(0,0,600,400)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("hsc", "ViewGroup onTouchEvent: $event")

        return super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("hsc", "ViewGroup onInterceptTouchEvent: $ev")

        return super.onInterceptTouchEvent(ev)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        //Log.d("hsc", "ViewGroup dispatchTouchEvent: $ev")

        return super.dispatchTouchEvent(ev)
    }

}