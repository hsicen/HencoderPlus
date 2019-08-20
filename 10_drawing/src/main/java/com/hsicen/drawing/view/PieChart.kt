package com.hsicen.drawing.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.hsicen.drawing.dp2px
import kotlin.math.cos
import kotlin.math.sin

/**
 * <p>作者：Hsicen  6/23/2019 11:06 AM
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：自定义饼图
 */
class PieChart @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {
    private val mRadius = 150f.dp2px
    private val mShift = 10f.dp2px
    private val mShiftIndex = 2

    private val mPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    private val mRect by lazy {
        RectF(width / 2f - mRadius, height / 2f - mRadius, width / 2f + mRadius, height / 2f + mRadius)
    }

    private val mColors = listOf(
        Color.parseColor("#156F11"),
        Color.parseColor("#CC33CC"),
        Color.parseColor("#8000FF"),
        Color.parseColor("#FF8000"),
        Color.parseColor("#800000")
    )

    private val mAngles = listOf(45f, 55f, 120f, 100f, 40f)


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var mStart = 0f
        for (index in 0 until mColors.size) {
            mPaint.color = mColors[index]

            //偏移突出效果
            if (mShiftIndex == index) {
                canvas.save()
                canvas.translate(
                    (cos(Math.toRadians(mStart + mAngles[index] / 2.0)) * mShift).toFloat(),
                    (sin(Math.toRadians(mStart + mAngles[index] / 2.0)) * mShift).toFloat()
                )
            }

            canvas.drawArc(mRect, mStart, mAngles[index], true, mPaint)
            mStart += mAngles[index]

            //恢复偏移
            if (mShiftIndex == index) canvas.restore()
        }
    }
}