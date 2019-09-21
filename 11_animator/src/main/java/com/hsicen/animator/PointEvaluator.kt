package com.hsicen.animator

import android.animation.TypeEvaluator
import android.graphics.PointF

/**
 * <p>作者：Hsicen  2019/7/19 16:12
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
class PointEvaluator : TypeEvaluator<PointF> {

    override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {

        val x = startValue.x + (endValue.x - startValue.x) * fraction
        val y = startValue.y + (endValue.y - startValue.y) * fraction

        return PointF(x, y)
    }
}