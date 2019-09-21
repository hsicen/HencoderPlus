package com.hsicen.multitouch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * <p>作者：Hsicen  2019/8/5 10:55
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：多点触控处理
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

    private var trackPointerId = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = resolveSize(600, widthMeasureSpec)
        val height = resolveSize(600, heightMeasureSpec)

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPaint.color = Color.parseColor("#D79BE3")
        canvas.drawRect(offsetX, offsetY, offsetX + 600f, offsetY + 600f, mPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                val actionIndex = event.actionIndex
                trackPointerId = event.getPointerId(actionIndex)

                downX = event.x
                downY = event.y
                imageX = offsetX
                imageY = offsetY
            }

            MotionEvent.ACTION_MOVE -> {
                val index = event.findPointerIndex(trackPointerId)
                offsetX = imageX + event.getX(index) - downX
                offsetY = imageY + event.getY(index) - downY
                invalidate()
            }

            MotionEvent.ACTION_POINTER_DOWN -> {
                val actionIndex = event.actionIndex
                trackPointerId = event.getPointerId(actionIndex)

                downX = event.getX(actionIndex)
                downY = event.getY(actionIndex)
                imageX = offsetX
                imageY = offsetY
            }

            MotionEvent.ACTION_POINTER_UP -> {
                var actionIndex = event.actionIndex

                //判断抬起的手指是否为当前起作用的手指
                if (trackPointerId == event.getPointerId(actionIndex)) {

                    // pointerCount 包含此刻抬起的手指
                    actionIndex = if (actionIndex == event.pointerCount - 1) {
                        //抬起的手指是最后按下的手指
                        event.pointerCount - 2
                    } else event.pointerCount - 1

                    trackPointerId = event.getPointerId(actionIndex)
                    downX = event.getX(actionIndex)
                    downY = event.getY(actionIndex)
                    imageX = offsetX
                    imageY = offsetY
                }
            }
        }

        return true
    }
}
