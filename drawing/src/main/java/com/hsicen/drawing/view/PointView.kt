package com.hsicen.drawing.view

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.PointFEvaluator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.hsicen.drawing.dp2px

/**
 * <p>作者：Hsicen  2019/7/1 14:48
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：Point滚动View位置颜色渐变动画实现
 */
class PointView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    private val mRadius = 20f.dp2px
    var mColor = Color.parseColor("#FFFF0000")
    var mPoint = PointF(25f.dp2px, 25f.dp2px)
        set(value) {
            field = value
            invalidate()
        }

    private val mPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPaint.style = Paint.Style.FILL
        mPaint.color = mColor
        canvas.drawCircle(mPoint.x, mPoint.y, mRadius, mPaint)
    }

    fun startAnimate() {
        mPoint = PointF(25f.dp2px, 25f.dp2px)
        mColor = Color.parseColor("#FFFF0000")

        val colorHolder = PropertyValuesHolder.ofObject("mColor", ArgbEvaluator(), Color.parseColor("#FF00FF00"))
        val pointHolder =
            PropertyValuesHolder.ofObject("mPoint", PointFEvaluator(), PointF(width - mRadius, height - mRadius))
        val animator = ObjectAnimator.ofPropertyValuesHolder(this, colorHolder, pointHolder)
        animator.duration = 5000
        animator.start()
    }
}