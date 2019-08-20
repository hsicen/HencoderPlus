package com.example.a21_multitouch.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import androidx.core.util.forEach

/**
 * <p>作者：Hsicen  2019/8/19 15:15
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：多点触控(各自为政型)
 */
class MultiTouchView2 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mPaths = SparseArray<Path>()
    private val mPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    init {
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 8f
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeJoin = Paint.Join.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPaths.forEach { _, value ->
            canvas.drawPath(value, mPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_POINTER_DOWN,
            MotionEvent.ACTION_DOWN -> {
                val pointerId = event.getPointerId(event.actionIndex)
                val path = Path()
                path.moveTo(event.getX(event.actionIndex), event.getY(event.actionIndex))
                mPaths.append(pointerId, path)
            }

            MotionEvent.ACTION_MOVE -> {
                //更新所有手指的路径
                for (index in 0 until event.pointerCount) {
                    val pointerId = event.getPointerId(index)
                    val path = mPaths.get(pointerId)
                    path.lineTo(event.getX(index), event.getY(index))
                }
            }

            MotionEvent.ACTION_POINTER_UP,
            MotionEvent.ACTION_UP -> {
                val pointerId = event.getPointerId(event.actionIndex)
                mPaths.delete(pointerId)
            }
        }

        invalidate()
        return true
    }

}
