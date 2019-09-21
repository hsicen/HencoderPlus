package com.hsicen.drawing.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hsicen.drawing.dp2px
import com.hsicen.drawing.sp2px

/**
 * <p>作者：Hsicen  6/26/2019 4:21 PM
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：Paint和Canvas相关Api的使用
 */
class PaintView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    private val mLinearGradient by lazy {
        LinearGradient(
            10f.dp2px, 10f.dp2px, 380f.dp2px, 380f.dp2px, Color.RED,
            Color.YELLOW, Shader.TileMode.CLAMP
        )
    }

    private val mPaint by lazy { Paint() }
    private val mPath by lazy { Path() }
    private val mDashArray = FloatArray(2)
    private val mDashEffect by lazy {
        mDashArray[0] = 10f
        mDashArray[1] = 20f
        DashPathEffect(mDashArray, 0f)
    }

    private val mMatrix = Matrix()
    private val mCamera = Camera()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPaint.pathEffect = mDashEffect
        mPaint.shader = mLinearGradient
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 2f.dp2px
        mPaint.color = Color.YELLOW
        mPath.addCircle(100f.dp2px, 100f.dp2px, 90f.dp2px, Path.Direction.CW)
        mPath.cubicTo(30f.dp2px, 30f.dp2px, 60f.dp2px, 120f.dp2px, 250f.dp2px, 380f.dp2px)
        mPath.quadTo(380f.dp2px, 10f.dp2px, 250f.dp2px, 30f.dp2px)

        canvas.drawPath(mPath, mPaint)
        mPaint.isAntiAlias = true
        canvas.drawCircle(width / 2f, height / 2f, 150f.dp2px, mPaint)

        canvas.save()
        mPaint.pathEffect = null
        mPaint.strokeWidth = 2f
        canvas.clipRect(50f.dp2px, 50f.dp2px, 300f.dp2px, 100f.dp2px)
        canvas.drawColor(Color.GREEN)

        mPaint.color = Color.RED
        mPaint.textSize = 20f.sp2px
        canvas.scale(0.5f, 0.5f, 175f.dp2px, 75f.dp2px)
        canvas.drawText("Hsicen", 60f.dp2px, 70f.dp2px, mPaint)
        canvas.restore()

        mMatrix.reset()
        mMatrix.preTranslate(20f, 90f)
        mMatrix.preRotate(30f)

        canvas.save()
        canvas.concat(mMatrix)
        canvas.drawText("Hsicen", 20f, 90f, mPaint)
        canvas.restore()

        canvas.save()

        mCamera.save()
        mCamera.rotate(13f, 5f, 6f)
        mCamera.applyToCanvas(canvas)
        mCamera.restore()

        mPaint.color = Color.BLACK
        canvas.drawText("Hsicen", 350f, 950f, mPaint)
        canvas.restore()

        mPaint.textSize = 75f.sp2px
        canvas.drawText("道", 250f, 950f, mPaint)

        canvas.drawText("术", 580f, 950f, mPaint)
    }
}
