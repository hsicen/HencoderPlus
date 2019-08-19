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
 * <p>描述： 多点触控
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

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = resolveSize(400, widthMeasureSpec)
        val height = resolveSize(400, heightMeasureSpec)

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPaint.color = Color.parseColor("#D79BE3")
        canvas.drawRect(offsetX, offsetY, offsetX + 600f, offsetY + 600f, mPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val count = event.pointerCount
        var sumX = 0f
        var sumY = 0f
        for (index in 0 until count) {
            sumX += event.getX(index)
            sumY += event.getY(index)
        }

        val focusX = sumX / count
        val focusY = sumY / count

        when (event.actionMasked) {
            MotionEvent.ACTION_POINTER_UP,
            MotionEvent.ACTION_POINTER_DOWN,
            MotionEvent.ACTION_DOWN -> {
                downX = focusX
                downY = focusY
                imageX = offsetX
                imageY = offsetY
            }

            MotionEvent.ACTION_MOVE -> {
                offsetX = imageX + focusX - downX
                offsetY = imageY + focusY - downY
                invalidate()
            }
        }

        return true
    }
}
