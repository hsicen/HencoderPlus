package com.hsicen.layout

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import java.util.*
import kotlin.math.max

/**
 * <p>作者：Hsicen  2019/7/29 9:06
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
class TagLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private val childBounds by lazy {
        ArrayList<Rect>()
    }

    //重写onMeasure  计算出每个子View的尺寸和左上右下的位置值
    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var usedWidth = 0
        var usedHeight = 0
        var lineHeight = 0
        var lineWidthUsed = 0
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        for (index in 0 until childCount) {
            val child = getChildAt(index)
            //难点1：计算出每个子View的widthMeasureSpec和heightMeasureSpec
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, usedHeight)
            //折行判断
            if (widthMode != MeasureSpec.UNSPECIFIED
                && lineWidthUsed + child.measuredWidth + child.marginLeft + child.marginRight > widthSize
            ) {
                lineWidthUsed = 0
                usedHeight += lineHeight
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, usedHeight)
            }

            //难点2 计算出child的左上右下位置, 然后保存
            val bound = if (childBounds.size <= index) {
                childBounds.add(Rect())
                childBounds[index]
            } else childBounds[index]
            bound.set(
                lineWidthUsed + child.marginLeft,
                usedHeight + child.marginTop,
                lineWidthUsed + child.measuredWidth + child.marginRight,
                usedHeight + child.measuredHeight + child.marginBottom
            )

            //更新使用的宽高
            lineWidthUsed += (child.measuredWidth + child.marginLeft + child.marginRight)
            usedWidth = max(lineWidthUsed, usedWidth)
            lineHeight =
                max(child.measuredHeight + child.marginTop + child.marginBottom, lineHeight)
        }

        //难点3 计算出自己的位置，然后保存
        val measureWidth = usedWidth
        usedHeight += lineHeight
        val measureHeight = usedHeight
        setMeasuredDimension(measureWidth, measureHeight)
    }

    //重写onLayout  设置每个子View的左上右下位置值(来源于onMeasure中计算出的值)
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (index in 0 until childCount) {
            val child = getChildAt(index)
            val bound = childBounds[index]
            child.layout(bound.left, bound.top, bound.right, bound.bottom)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

}