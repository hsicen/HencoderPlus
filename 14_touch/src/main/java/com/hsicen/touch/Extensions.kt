package com.hsicen.touch

import android.content.res.Resources
import android.util.Log
import android.util.TypedValue

/**
 * <p>作者：Hsicen  6/22/2019 12:52 PM
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：全局扩展
 */

val Float.dp2px: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
    )

val Float.sp2px: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics
    )

fun logd(msg: String, tag: String = "hsc") {
    Log.d(tag, msg)
}