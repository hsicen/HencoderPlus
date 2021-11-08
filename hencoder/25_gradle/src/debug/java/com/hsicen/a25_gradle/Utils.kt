package com.hsicen.a25_gradle

import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * <p>作者：hsicen  2019/12/22 11:37
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
object Utils {

    fun drawBadge(act: Context) {
        /*val decorView = act.window.decorView as ViewGroup
        val badge = TextView(act)
        badge.setBackgroundColor(Color.YELLOW)
        decorView.addView(badge, 200, 150)*/

        val bundle = Bundle()
        val intent = Intent(act, MainActivity::class.java)

        act.startActivity(intent, bundle)
    }
}