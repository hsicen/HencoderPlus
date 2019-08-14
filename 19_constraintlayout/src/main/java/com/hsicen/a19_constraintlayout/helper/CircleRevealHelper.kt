package com.hsicen.a19_constraintlayout.helper

import android.content.Context
import android.util.AttributeSet
import android.view.ViewAnimationUtils
import androidx.constraintlayout.widget.ConstraintHelper
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.hypot

/**
 * <p>作者：Hsicen  19-8-11 上午11:48
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：控件揭控件点击波浪露动画(控件入场动画)
 */
class CircleRevealHelper(context: Context, attrs: AttributeSet) : ConstraintHelper(context, attrs) {

    /**
     * 在ConstraintLayout的onLayout方法中调用
     */
    override fun updatePostLayout(container: ConstraintLayout?) {
        super.updatePostLayout(container)

        for (referId in referencedIds) {
            val view = container!!.getViewById(referId)
            val radius = hypot(view.width.toDouble(), view.height.toDouble()).toFloat()
            ViewAnimationUtils.createCircularReveal(view, 0, 0, 0f, radius)
                .setDuration(2000L)
                .start()
        }
    }

}