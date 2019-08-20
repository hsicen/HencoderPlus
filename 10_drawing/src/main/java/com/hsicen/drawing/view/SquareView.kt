package com.hsicen.drawing.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * <p>作者：Hsicen  2019/7/3 9:37
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：宽高适配的ImageView
 */
class SquareView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ImageView(context, attrs, defStyleAttr) {


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = measuredWidth
        val height = measuredHeight

        if (width > height) {
            setMeasuredDimension(height, height)
        } else {
            setMeasuredDimension(width, width)
        }
    }
}