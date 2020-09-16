package com.hsicen.animator.test

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.hsicen.animator.dp2px
import com.hsicen.animator.sp2px

/**
 * <p>作者：Hsicen  2019/6/30 19:05
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：百分比View
 */
class SportView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mRadius = 150f.dp2px
    private val mBounds = Rect()

    private val mPaint by lazy {
        Paint()
    }

    private var progress = 0
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //绘制底色
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 10f.dp2px
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.color = Color.GRAY
        canvas.drawArc(
            width / 2f - mRadius,
            height / 2f - mRadius,
            width / 2f + mRadius,
            height / 2f + mRadius,
            140f,
            360f,
            false,
            mPaint
        )

        //绘制文字
        mPaint.style = Paint.Style.FILL
        val mText = "${progress * 100 / 360} %"
        mPaint.textAlign = Paint.Align.CENTER
        mPaint.color = Color.BLACK
        mPaint.textSize = 60f.sp2px
        mPaint.getTextBounds(mText, 0, mText.length, mBounds)
        val offsetY = (mBounds.top + mBounds.bottom) / 2f
        canvas.drawText(mText, width / 2f, height / 2f - offsetY, mPaint)

        //绘制进度
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 10f.dp2px
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.color = Color.parseColor("#E91B63")
        canvas.drawArc(
            width / 2f - mRadius,
            height / 2f - mRadius,
            width / 2f + mRadius,
            height / 2f + mRadius,
            140f,
            progress.toFloat(),
            false,
            mPaint
        )
    }

    fun startProgress() {
        val objectAnimator = ObjectAnimator.ofInt(this, "progress", 0, 361, 250, 360)
        objectAnimator.duration = 2000L
        objectAnimator.interpolator = AccelerateDecelerateInterpolator()
        objectAnimator.start()
    }
}