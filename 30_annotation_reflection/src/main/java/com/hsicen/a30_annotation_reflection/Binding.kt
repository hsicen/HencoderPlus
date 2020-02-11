package com.hsicen.a30_annotation_reflection

import android.app.Activity
import android.util.Log

/**
 * <p>作者：hsicen  2020/2/9 15:32
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
object Binding {

    fun bind(activity: Activity){
        activity.javaClass.declaredFields.forEach {filed ->
            val bindView = filed.getAnnotation(BindView::class.java)
            if (bindView != null){
                try {
                    filed.isAccessible = true
                    filed.set(activity, activity.findViewById(bindView.value))
                } catch (e: Exception) {
                    Log.d("hsc", "反射出错 ${e.printStackTrace()}")
                }
            }
        }
    }
}