package com.hsicen.drawing.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.hsicen.drawing.dp2px
import com.hsicen.drawing.sp2px

/**
 * <p>作者：Hsicen  6/24/2019 7:20 PM
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：自定义圆形进度条
 */
class CircleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    //圆环半径
    private val mRadius = 100f.dp2px
    private val mStrokeWidth = 20f.dp2px

    private val mPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    private val mBounds by lazy {
        Rect()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //绘制底色圆环
        mPaint.color = Color.parseColor("#C0C0C0")
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = mStrokeWidth
        canvas.drawCircle(width / 2f, height / 2f, mRadius, mPaint)

        //绘制圆环进度
        mPaint.color = Color.parseColor("#FF8040")
        mPaint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            width / 2f - mRadius, height / 2f - mRadius, width / 2f + mRadius, height / 2f + mRadius,
            0f, 230f, false, mPaint
        )

        //绘制文字(居中)
        val mText = "ababg"
        mPaint.textAlign = Paint.Align.CENTER
        mPaint.color = Color.parseColor("#00B000")
        mPaint.style = Paint.Style.FILL
        mPaint.textSize = 50f.sp2px

        mPaint.getTextBounds(mText, 0, mText.length, mBounds)
        val offsetY = (mBounds.top + mBounds.bottom) / 2
        canvas.drawText(mText, width / 2f, height / 2f - offsetY, mPaint)

        //绘制文字(贴边)
        mPaint.textAlign = Paint.Align.LEFT
        canvas.drawText(mText, 0f, 0f, mPaint)

        //绘制文字(贴顶)
        canvas.drawText(mText, mBounds.right + 10f.dp2px, -mBounds.top.toFloat(), mPaint)

        //贴左
        mPaint.getTextBounds(mText, 0, mText.length, mBounds)
        canvas.drawText(mText, -mBounds.left.toFloat(), -mBounds.top.toFloat() + mPaint.fontSpacing, mPaint)
    }
}