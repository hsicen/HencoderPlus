package com.hsicen.drawable

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 * <p>作者：Hsicen  2019/7/22 15:35
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：DrawableView
 */
class DrawableView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mDrawable = MeshDrawable()


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mDrawable.setBounds(0, 0, width, height)
        mDrawable.draw(canvas)
    }
}