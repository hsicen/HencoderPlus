package com.hsicen.drawing.test

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hsicen.drawing.R
import com.hsicen.drawing.dp2px
import kotlin.math.cos
import kotlin.math.sin

/**
 * 作者：hsicen  2020/9/13 12:14
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：自定义仪表盘控件
 */
class DashView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    private var openAngle = 120f
    private var mRadius = 100f.dp2px
    private var mark = 20
    private var markPos = 0
    private var markLength = 80f.dp2px

    private val mArcPath = Path()
    private var mArcPathMeasure = PathMeasure(mArcPath, false)

    private val dash by lazy {
        Path().apply {
            addRect(0f, 0f, 2f.dp2px, 10f.dp2px, Path.Direction.CW)
        }
    }
    private var dashEffect = PathDashPathEffect(
        dash,
        (mArcPathMeasure.length - 2f.dp2px) / mark,
        0f,
        PathDashPathEffect.Style.ROTATE
    )

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.DashView)
        openAngle = typeArray.getFloat(R.styleable.DashView_openAngle, openAngle)
        mark = typeArray.getInt(R.styleable.DashView_mark, mark)
        markLength = typeArray.getDimension(R.styleable.DashView_markLength, markLength)
        markPos = typeArray.getInt(R.styleable.DashView_markPos, markPos)
        mRadius = typeArray.getDimension(R.styleable.DashView_radius, mRadius)

        typeArray.recycle()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mArcPath.reset()
        mArcPath.addArc(
            width / 2f - mRadius,
            height / 2f - mRadius,
            width / 2f + mRadius,
            height / 2f + mRadius,
            90 + openAngle / 2,
            360 - openAngle
        )

        mArcPathMeasure = PathMeasure(mArcPath, false)
        dashEffect = PathDashPathEffect(
            dash,
            (mArcPathMeasure.length - 2f.dp2px) / mark,
            0f,
            PathDashPathEffect.Style.ROTATE
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //绘制圆弧
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 3f.dp2px
        canvas.drawPath(mArcPath, mPaint)

        //绘制刻度
        mPaint.style = Paint.Style.FILL
        mPaint.pathEffect = dashEffect
        canvas.drawPath(mArcPath, mPaint)
        mPaint.pathEffect = null

        //绘制原点
        canvas.drawCircle(width / 2f, height / 2f, 2f.dp2px, mPaint)

        //绘制指针
        mPaint.strokeWidth = 2f.dp2px
        canvas.drawLine(
            width / 2f, height / 2f,
            width / 2f + markLength * cos(getAngleForPos(markPos)),
            height / 2f + markLength * sin(getAngleForPos(markPos)),
            mPaint
        )

    }

    private fun getAngleForPos(markPos: Int): Float {
        //转换成弧度值
        return Math.toRadians(((90 + openAngle / 2) + markPos * (360 - openAngle) / mark).toDouble())
            .toFloat()
    }

}