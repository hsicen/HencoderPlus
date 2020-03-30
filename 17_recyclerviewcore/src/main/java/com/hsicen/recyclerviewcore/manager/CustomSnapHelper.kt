package com.hsicen.recyclerviewcore.manager

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

/**
 * 作者：hsicen  2020/3/27 16:27
 * 邮箱：codinghuang@163.com
 * 作用：RecyclerView 滑动对齐助手
 * 描述：
 */
class CustomSnapHelper : SnapHelper() {

    override fun calculateDistanceToFinalSnap(
        layoutManager: RecyclerView.LayoutManager,
        targetView: View
    ): IntArray? {
        //计算滑动距离  0为水平滑动距离 1位垂直滑动距离
        if (layoutManager is CustomLayoutManager) {
            val out = IntArray(2)
            if (layoutManager.canScrollHorizontally()) {
                out[0] =
                    layoutManager.calculateDistanceToPosition(layoutManager.getPosition(targetView))
                out[1] = 0
            } else {
                out[0] = 0
                out[1] =
                    layoutManager.calculateDistanceToPosition(layoutManager.getPosition(targetView))
            }

            return out
        }

        return null
    }

    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager?,
        velocityX: Int,
        velocityY: Int
    ): Int = RecyclerView.NO_POSITION

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager?): View? {
        if (layoutManager is CustomLayoutManager) {
            val position = layoutManager.getFixedScrollPosition()
            if (position != RecyclerView.NO_POSITION) {
                return layoutManager.findViewByPosition(position)
            }
        }

        return null
    }

}