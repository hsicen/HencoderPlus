package com.hsicen.drawable

import android.graphics.*
import android.graphics.drawable.Drawable

/**
 * <p>作者：Hsicen  2019/7/22 16:36
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：自定义Drawable可用于自定义View中的公共部分
 *
 * Drawable也是使用Canvas来进行绘制的
 */
class MeshDrawable : Drawable() {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mLines = 20

    override fun draw(canvas: Canvas) {
        mPaint.color = Color.parseColor("#0080FF")
        mPaint.strokeWidth = 2f.dp2px
        val widthGap = bounds.width() / mLines * 1f
        val heightGap = bounds.height() / mLines * 1f

        for (index in 0..mLines) {
            canvas.drawLine(
                index * widthGap,
                bounds.top.toFloat(),
                index * widthGap,
                bounds.bottom.toFloat(),
                mPaint
            )

            canvas.drawLine(
                bounds.left.toFloat(),
                index * heightGap,
                bounds.right.toFloat(),
                index * heightGap,
                mPaint
            )
        }
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun getAlpha() = mPaint.alpha

    override fun getOpacity(): Int {
        return when (mPaint.alpha) {
            0XFF -> PixelFormat.OPAQUE
            0 -> PixelFormat.TRANSPARENT
            else -> PixelFormat.TRANSLUCENT
        }
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }
}