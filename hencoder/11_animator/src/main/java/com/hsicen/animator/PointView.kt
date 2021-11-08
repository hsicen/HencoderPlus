package com.hsicen.animator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

/**
 * <p>作者：Hsicen  2019/7/19 16:25
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
class PointView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var mPoint = PointF(25f.dp2px, 25f.dp2px)
      set(value) {
        field = value
        invalidate()
      }

    var mColor = Color.parseColor("#ff0000")
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
        canvas.drawCircle(mPoint.x, mPoint.y, 20f.dp2px, mPaint)
    }
}