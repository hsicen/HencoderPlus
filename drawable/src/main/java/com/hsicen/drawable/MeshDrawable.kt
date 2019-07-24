package com.hsicen.drawable

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

/**
 * <p>作者：Hsicen  2019/7/22 16:36
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
class MeshDrawable : Drawable() {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun draw(canvas: Canvas) {

    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun getAlpha() = mPaint.alpha

    override fun getOpacity(): Int {
        return when (mPaint.alpha) {
            0XFF -> PixelFormat.TRANSPARENT
            0 -> PixelFormat.TRANSLUCENT
            else -> PixelFormat.UNKNOWN
        }
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }
}