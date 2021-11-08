@file:JvmName("DpUtils")

package com.hsicen.kolitn

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast

/**
 * <p>作者：hsicen  2019/11/22 14:46
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：扩展方法定义
 */

/***px2dp*/

fun Float.dp(): Float {
    //逻辑处理。。。。
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )
}

fun String.toast(duration: Int = Toast.LENGTH_SHORT) {

}