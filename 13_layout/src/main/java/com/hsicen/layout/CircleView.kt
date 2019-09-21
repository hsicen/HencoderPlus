package com.hsicen.layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * <p>作者：Hsicen  2019/7/29 8:27
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
class CircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mRadius = 80f.dp2px
    private val mPadding = 16f.dp2px
    private val mPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var mWidth = (mPadding + mRadius).toInt() * 2
        var mHeight = (mPadding + mRadius).toInt() * 2

        mWidth = resolveSize(mWidth, widthMeasureSpec)
        mHeight = resolveSize(mHeight, heightMeasureSpec)
        setMeasuredDimension(mWidth, mHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPaint.color = Color.YELLOW
        canvas.drawCircle(mPadding + mRadius, mPadding + mRadius, mRadius, mPaint)
    }
}