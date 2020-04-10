package com.hsicen.touch

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat

/**
 * <p>作者：Hsicen  2019/7/30 10:01
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：自定义触摸反馈算法
 */
class TouchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    //复杂手势操作  接替onTouchEvent处理事件分发
    private val mGestureDetector = GestureDetectorCompat(context,
        object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent?): Boolean {
                logd("hsc", "双击操作")
                return true
            }

            override fun onDown(e: MotionEvent?): Boolean {

                return true
            }

            override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                logd("hsc", "单击操作")
                return super.onSingleTapConfirmed(e)
            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                logd("hsc", "单击抬起")
                return super.onSingleTapUp(e)
            }
        })

    /*** 自定义触摸反馈算法*/
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return mGestureDetector.onTouchEvent(event)
    }

}