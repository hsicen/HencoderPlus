package com.hsicen.drawing.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.TextView

/**
 * <p>作者：Hsicen  2019/6/30 12:02
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：绘制顺序
 */
class OrderView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        TextView(context, attrs, defStyleAttr) {
    private val mPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    override fun onDraw(canvas: Canvas) {
        //绘制底色
        mPaint.color = Color.parseColor("#A8D7C0")
        canvas.drawColor(Color.parseColor("#A8D7C0"))

        super.onDraw(canvas)
    }
}