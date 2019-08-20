package com.hsicen.drawing.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.hsicen.drawing.dp2px

/**
 * <p>作者：Hsicen  2019/7/16 8:41
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
class CircleView2 @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    private var radius = 25f.dp2px
        get() = field
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
        canvas.drawCircle(width / 2f, height / 2f, radius, mPaint)
    }

}