package com.hsicen.layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.widget.TextView
import java.util.*


/**
 * <p>作者：Hsicen  2019/7/29 14:09
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
class ColorTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    private val mColors = intArrayOf(
        Color.parseColor("#E91E63"),
        Color.parseColor("#673AB7"),
        Color.parseColor("#3F51B5"),
        Color.parseColor("#2196F3"),
        Color.parseColor("#009688"),
        Color.parseColor("#FF9800"),
        Color.parseColor("#FF5722"),
        Color.parseColor("#795548")
    )
    private val mTextSize = floatArrayOf(16f, 22f, 28f)
    private val mCornerRadius = 4f.dp2px
    private val mXPadding = 16.dp2px
    private val mYPadding = 8.dp2px

    private val random = Random()
    private val paint = Paint(ANTI_ALIAS_FLAG)

    init {
        setTextColor(Color.WHITE)
        textSize = mTextSize[random.nextInt(3)]
        paint.color = mColors[random.nextInt(mColors.size)]
        setPadding(mXPadding, mYPadding, mXPadding, mYPadding)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            mCornerRadius,
            mCornerRadius,
            paint
        )
        super.onDraw(canvas)
    }

}