package com.hsicen.drawing.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hsicen.drawing.dp2px
import kotlin.math.cos
import kotlin.math.sin

/**
 * <p>作者：Hsicen  6/22/2019 11:54 AM
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：仪表盘
 */
class DashBord @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    private val mRadius = 150f.dp2px
    private val mLeftAngle = 120f
    private val mLength = 130f.dp2px

    private val mPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = 2f.dp2px
        }
    }

    private val dash by lazy {
        Path().apply {
            addRect(0f, 0f, 2f.dp2px, 10f.dp2px, Path.Direction.CCW)
        }
    }

    private val dashEffect by lazy {
        PathDashPathEffect(
            dash,
            (mPathMeasure.length - 2f.dp2px) / 20, //设置两个点之间的间隔
            0f, //设置提前间隔多少像素
            PathDashPathEffect.Style.ROTATE
        )
    }

    private val mArcPath by lazy {
        Path()
    }

    private val mPathMeasure by lazy {
        PathMeasure(mArcPath, false)
    }

    private var pointer = 0
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val xOffset = 0//mRadius * cos(Math.toRadians(mLeftAngle / 2.0)).toFloat() - paddingLeft * 2

        //绘制扇形
        mArcPath.addArc(
            width / 2 - mRadius - xOffset,
            height / 2 - mRadius,
            width / 2 + mRadius - xOffset,
            height / 2 + mRadius,
            90 + mLeftAngle / 2,
            360 - mLeftAngle
        )
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 3f.dp2px
        canvas.drawPath(mArcPath, mPaint)

        //绘制刻度
        mPaint.pathEffect = dashEffect
        canvas.drawPath(mArcPath, mPaint)
        mPaint.pathEffect = null

        //绘制原点
        mPaint.style = Paint.Style.FILL
        canvas.drawCircle(width / 2f - xOffset, height / 2f, 4f.dp2px, mPaint)

        //绘制指针
        canvas.drawLine(
            width / 2f - xOffset,
            height / 2f,
            (width / 2f - xOffset + cos(Math.toRadians(getAngleForMark(pointer))) * mLength).toFloat(),
            (height / 2f + sin(Math.toRadians(getAngleForMark(pointer))) * mLength).toFloat(),
            mPaint
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width =
            mRadius + paddingLeft + paddingRight + mRadius * cos(Math.toRadians(mLeftAngle / 2.0)).toFloat()
        val height = mRadius * 2 + paddingTop + paddingBottom

        setMeasuredDimension(
            resolveSize(width.toInt(), widthMeasureSpec),
            resolveSize(height.toInt(), heightMeasureSpec)
        )
    }

    private fun getAngleForMark(position: Int): Double {
        return ((180 + mLeftAngle / 2) + position * (360 - mLeftAngle) / 20).toDouble()
    }
}