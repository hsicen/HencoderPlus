package com.hsicen.drawing.view.evaluator

import android.animation.TypeEvaluator
import android.graphics.Color

/**
 * <p>作者：Hsicen  2019/7/1 14:04
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：自定义颜色渐变估值器
 */
class HsvEvaluator : TypeEvaluator<Int> {
    private val startHsv = FloatArray(3)
    private val endHsv = FloatArray(3)
    private val outHsv = FloatArray(3)

    override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
        //把ARGB转换成HSV
        Color.colorToHSV(startValue, startHsv)
        Color.colorToHSV(endValue, endHsv)

        //计算当前动画完成度所对应的颜色值
        if (endHsv[0] - startHsv[0] > 180)
            endHsv[0] = endHsv[0] - 360
        else
            endHsv[0] = endHsv[0] + 360

        outHsv[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * fraction

        if (outHsv[0] > 360)
            outHsv[0] = outHsv[0] - 360
        else if (outHsv[0] < 0)
            outHsv[0] = outHsv[0] + 360

        outHsv[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * fraction
        outHsv[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * fraction

        //计算当前动画完成度所对应的透明度
        val alpha = (startValue ushr 24) + ((endValue ushr 24 - startValue ushr 24) * fraction).toInt()

        return Color.HSVToColor(alpha, outHsv)
    }

}