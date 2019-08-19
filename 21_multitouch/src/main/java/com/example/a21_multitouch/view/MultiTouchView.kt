package com.example.a21_multitouch.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * <p>作者：Hsicen  19-8-18 下午7:25
 * <p>邮箱：codinghuang@163.com
 * <p>功能：
 * <p>描述： 多点触控(协作型)
 */
class MultiTouchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var offsetX = 0f
    private var offsetY = 0f

    private var downX = 0f
    private var downY = 0f
    private var imageX = 0f
    private var imageY = 0f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPaint.color = Color.parseColor("#D79BE3")
        canvas.drawRect(offsetX, offsetY, offsetX + 500f, offsetY + 500f, mPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var sumX = 0f
        var sumY = 0f
        var count = event.pointerCount
        val isPointerUp = event.actionMasked == MotionEvent.ACTION_POINTER_UP
        for (index in 0 until count) {
            if (isPointerUp && event.actionIndex == index) continue
            sumX += event.getX(index)
            sumY += event.getY(index)
        }

        if (isPointerUp) count--
        val focusX = sumX / count
        val focusY = sumY / count

        when (event.actionMasked) {
            MotionEvent.ACTION_POINTER_UP,
            MotionEvent.ACTION_POINTER_DOWN,
            MotionEvent.ACTION_DOWN -> {
                //重置初始坐标
                downX = focusX
                downY = focusY
                imageX = offsetX
                imageY = offsetY
            }

            MotionEvent.ACTION_MOVE -> {
                //更新坐标
                offsetX = imageX + focusX - downX
                offsetY = imageY + focusY - downY
                invalidate()
            }
        }

        return true
    }
}
