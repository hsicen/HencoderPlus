package com.hsicen.layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

/**
 * <p>作者：Hsicen  19-7-28 下午4:49
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：Square ImageView
 */
class SquareImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        println("before layout")
        super.layout(l, t, r, b)
        println("after layout")

        println("direct after:   $width*$height")
        println("measure after:   $measuredWidth*$measuredHeight")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        println("before onLayout")
        super.onLayout(changed, left, top, right, bottom)
        println("after onLayout")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        println("direct before:   $width*$height")
        println("measure before:   $measuredWidth*$measuredHeight")
        println("before onMeasure")

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        println("after onMeasure")
        val size = min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }

    override fun draw(canvas: Canvas) {
        println("before draw")
        super.draw(canvas)
        println("after draw")

        mPaint.textAlign = Paint.Align.CENTER
        mPaint.textSize = 30f
        mPaint.color = Color.YELLOW
        canvas.drawText("$width * $height", width / 2f, height / 2f, mPaint)
    }

    override fun onDraw(canvas: Canvas?) {
        println("before onDraw")
        super.onDraw(canvas)
        println("after onDraw")
    }
}
