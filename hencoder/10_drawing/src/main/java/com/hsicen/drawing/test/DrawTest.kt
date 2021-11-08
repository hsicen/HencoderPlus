package com.hsicen.drawing.test

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hsicen.drawing.dp2px

/**
 * 作者：hsicen  2020/4/9 14:29
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：图形的位置测量
 */
class DrawTest @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    private val mTextRect by lazy {
        Rect()
    }

    private val mPath by lazy {
        Path()
    }

    private val mMetrics by lazy {
        Paint.FontMetrics()
    }

    private val mPathMeasure = PathMeasure()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val mRadius = width / 3f
        mPath.fillType = Path.FillType.EVEN_ODD
        mPath.addCircle(width / 2f, height / 2f, mRadius, Path.Direction.CW)
        mPath.addRect(
            width / 2f - mRadius,
            height / 2f,
            width / 2f + mRadius,
            height / 2f + mRadius * 2,
            Path.Direction.CW
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.apply {
            color = Color.GREEN
            strokeWidth = 2f.dp2px
            style = Paint.Style.FILL
            textSize = 16f.dp2px
        }

        //draw text
        val str = "Hello World"
        mPaint.getTextBounds(str, 0, str.length, mTextRect)  //获取文字尺寸
        canvas?.drawText(str, 2 * mTextRect.left.toFloat(), -mTextRect.top.toFloat(), mPaint)

        //draw rect
        canvas?.drawRoundRect(
            2 * mTextRect.left.toFloat(),
            -2 * mTextRect.top.toFloat(),
            300f.dp2px,
            150f.dp2px,
            10f.dp2px,
            10f.dp2px,
            mPaint
        )

        //with path
        canvas?.drawPath(mPath, mPaint)
        mPathMeasure.length
        //mPathMeasure.getPosTan()

        mPaint.getFontMetrics(mMetrics)
    }

}