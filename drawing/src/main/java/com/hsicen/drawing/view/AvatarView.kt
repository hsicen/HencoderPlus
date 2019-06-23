package com.hsicen.drawing.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView
import com.hsicen.drawing.dp2px

/**
 * <p>作者：Hsicen  6/23/2019 6:35 PM
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：自定义圆形View
 */
class AvatarView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ImageView(context, attrs, defStyleAttr) {

    private val mPadding = 0f.dp2px

    private val mBounds by lazy {
        RectF(mPadding, mPadding,
                mPadding + width.toFloat(),
                mPadding + height.toFloat())
    }

    private val mXfermode by lazy {
        PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    }

    private val mPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    override fun onDraw(canvas: Canvas) {

        mPaint.color = Color.parseColor("#FF0080")
        val layer = canvas.saveLayer(mBounds, mPaint)
        canvas.drawOval(mBounds, mPaint)
        mPaint.xfermode = mXfermode
        super.onDraw(canvas)
        mPaint.xfermode = null
        canvas.restoreToCount(layer)
    }

}