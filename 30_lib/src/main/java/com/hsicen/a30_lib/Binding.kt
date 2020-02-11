package com.hsicen.a30_lib

import android.app.Activity
import android.util.Log

/**
 * 作者：hsicen  2020/2/9 17:20
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：注解
 */
object Binding {

    fun bind(activity: Activity) {
        //绑定类所生成的类
        try {
            val bindClass = Class.forName(activity.javaClass.canonicalName + "Binding")
            val constructor = bindClass.getDeclaredConstructor(activity.javaClass)
            constructor.newInstance(activity)
        } catch (e: Exception) {
            Log.d("hsc", "反射出错  ${e.printStackTrace()}")
        }
    }
}