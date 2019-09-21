package com.hsicen.drawing.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.hsicen.drawing.dp2px
import com.hsicen.drawing.view.evaluator.HsvEvaluator

/**
 * <p>作者：Hsicen  2019/7/1 11:18
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：颜色渐变动画
 */
class ColorView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    private val mRadius = 150f.dp2px
    var mColor: Int = 0xffff0000.toInt()
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
        canvas.drawCircle(width / 2f, height / 2f, mRadius, mPaint)
    }

    fun startAnimate() {

        val animator =
            ObjectAnimator.ofInt(this, "mColor", 0xff00ff00.toInt())
        animator.setEvaluator(HsvEvaluator())

        animator.duration = 2000
        animator.start()
    }
}