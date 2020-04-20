package com.hsicen.a25_gradle

import android.app.Activity
import android.graphics.Color
import android.view.ViewGroup
import android.widget.TextView

/**
 * <p>作者：hsicen  2019/12/22 11:37
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
object Utils {

    fun drawBadge(act: Activity) {
        val decorView = act.window.decorView as ViewGroup
        val badge = TextView(act)
        badge.setBackgroundColor(Color.YELLOW)
        decorView.addView(badge, 200, 150)
    }
}