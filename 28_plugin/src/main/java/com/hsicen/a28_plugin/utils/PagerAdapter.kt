package com.hsicen.a28_plugin.utils

import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.viewpager.widget.PagerAdapter

/**
 * 作者：hsicen  2020/5/7 16:33
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class PagerAdapter: PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return false
    }

    override fun getCount(): Int {
        return 0
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position)
    }

}