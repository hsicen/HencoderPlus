package com.hsicen.drawing.test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.hsicen.drawing.dp2px
import java.lang.Math.toRadians
import kotlin.math.cos
import kotlin.math.sin

/**
 * 作者：hsicen  2020/9/13 21:26
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class PieView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    private val ANGLES = listOf(35f, 75f, 100f, 150f)
    private val COLORS = listOf("#5A7FBB", "#8ABB60", "#D19D4C", "#45D1AB")
    private val RADIUS = 150f.dp2px
    private val mBounds = RectF()
    private val offset = 2
    private val offsetLen = 5f.dp2px

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mBounds.set(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var currentAngle = 0f
        ANGLES.forEachIndexed { index, _ ->
            if (index == offset) {
                canvas.save()
                canvas.translate(
                    (offsetLen * cos(toRadians((currentAngle + ANGLES[index] / 2).toDouble()))).toFloat(),
                    (offsetLen * sin(toRadians((currentAngle + ANGLES[index] / 2).toDouble()))).toFloat()
                )
            }

            mPaint.color = Color.parseColor(COLORS[index])
            canvas.drawArc(mBounds, currentAngle, ANGLES[index], true, mPaint)
            currentAngle += ANGLES[index]

            if (index == offset) {
                canvas.restore()
            }
        }
    }
}
