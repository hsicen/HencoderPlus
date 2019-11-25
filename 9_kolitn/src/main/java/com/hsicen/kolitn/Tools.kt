package com.hsicen.kolitn

import android.util.Log

/**
 * <p>作者：hsicen  2019/11/22 14:46
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：扩展方法定义
 */

/***px2dp*/
fun Int.dp(): Int {
    //逻辑处理。。。。

    return this * 3
}


fun main() {

    val dpValue = 12.dp()
    Log.d("hsc", "This is the value of  $dpValue")
}