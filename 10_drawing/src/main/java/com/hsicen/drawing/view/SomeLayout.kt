package com.hsicen.drawing.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * <p>作者：Hsicen  2019/7/5 9:20
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：自定义View Group
 */
class SomeLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ViewGroup(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        for (index in 0..childCount) {
            val child = getChildAt(index)

        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }
}