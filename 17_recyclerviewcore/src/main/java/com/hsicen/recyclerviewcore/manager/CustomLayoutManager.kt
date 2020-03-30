package com.hsicen.recyclerviewcore.manager

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

/**
 * 作者：hsicen  2020/3/26 16:53
 * 邮箱：codinghuang@163.com
 * 作用：自定义LayoutManager
 * 描述：水平滑动的RecyclerViewManager
 */
class CustomLayoutManager(private val itemHWRatio: Float, private val scale: Float) :
    RecyclerView.LayoutManager() {

    private var mItemViewHeight = 0
    private var mItemViewWidth = 0
    private var mItemCount = 0
    private var mScrollOffset = Int.MAX_VALUE

    private var mHasChild = false
    private val mSnapHelper by lazy { CustomSnapHelper() }

    override fun onAttachedToWindow(view: RecyclerView?) {
        super.onAttachedToWindow(view)
        mSnapHelper.attachToRecyclerView(view)
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams =
        RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )

    override fun canScrollHorizontally(): Boolean {
        return true
    }

    override fun scrollHorizontallyBy(
        dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?
    ): Int {
        recycler ?: return 0

        val pendingScrollOffset = mScrollOffset + dx
        mScrollOffset = makeScrollOffsetWithRange(pendingScrollOffset)
        fill(recycler)

        return mScrollOffset - pendingScrollOffset + dx
    }

    //ItemView的位置测量
    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        state ?: return
        recycler ?: return
        if (0 == state.itemCount || state.isPreLayout) return

        if (!mHasChild) {
            mHasChild = true
            mItemViewHeight = getVerticalSpace()
            mItemViewWidth = (mItemViewHeight / itemHWRatio).toInt()
        }

        mItemCount = itemCount
        mScrollOffset = makeScrollOffsetWithRange(mScrollOffset)
        fill(recycler)
    }

    private fun getVerticalSpace(): Int {
        return height - paddingTop - paddingBottom
    }

    private fun getHorizontalSpace(): Int {
        return width - paddingLeft - paddingRight
    }

    private fun fill(recycler: RecyclerView.Recycler) {
        //1. 前置数据准备
        var bottomVisiblePosition = mScrollOffset / mItemViewWidth
        val bottomVisibleSize = mScrollOffset % mItemViewWidth
        val offsetPercent = bottomVisibleSize.toFloat() / mItemViewWidth
        val space = getHorizontalSpace()
        var remainSpace = space.toFloat()
        val defaultOffset = mItemViewWidth / 2
        val itemViewInfos = mutableListOf<ItemViewInfo>()

        //2. 计算每个ItemView的位置信息(left,scale)
        var j = 1
        for (i in (bottomVisiblePosition - 1).downTo(0)) {
            val maxOffset = defaultOffset * scale.pow(j - 1)
            val start = (remainSpace - offsetPercent * maxOffset - mItemViewWidth).toInt()
            val itemInfo =
                ItemViewInfo(start, scale.pow(j - 1) * (1 - offsetPercent * (1 - scale)))
            itemViewInfos.add(0, itemInfo)
            remainSpace -= maxOffset

            if (remainSpace < 0) {
                itemInfo.left = (remainSpace + maxOffset - mItemViewWidth).toInt()
                itemInfo.scale = scale.pow(j - 1)
                break
            }

            j++
        }

        //3. 添加最右边的ItemView相关信息
        if (bottomVisiblePosition < mItemCount) {
            val left = space - bottomVisibleSize
            itemViewInfos.add(ItemViewInfo(left, 1.0f))
        } else bottomVisiblePosition -= 1

        //4. 回收不可见ItemView
        val layoutCount = itemViewInfos.size //可见ItemView数量
        val startPosition = bottomVisiblePosition - (layoutCount - 1)
        val endPosition = bottomVisiblePosition
        val childCount = childCount
        for (index in (childCount - 1).downTo(0)) {
            val childView = getChildAt(index)
            val position = convertPosition(index)

            if (position > endPosition || position < startPosition) {
                detachAndScrapView(childView!!, recycler)
            }
        }

        //5. 布局View
        detachAndScrapAttachedViews(recycler)
        for (index in 0 until layoutCount) {
            fillChild(
                recycler.getViewForPosition(convertPosition(startPosition + index)),
                itemViewInfos[index]
            )
        }
    }

    private fun fillChild(itemView: View, itemViewInfo: ItemViewInfo) {
        addView(itemView)
        measureChildWithExactSize(itemView)

        val top = paddingTop
        layoutDecoratedWithMargins(
            itemView,
            itemViewInfo.left,
            top,
            itemViewInfo.left + mItemViewWidth,
            top + mItemViewHeight
        )

        itemView.scaleX = itemViewInfo.scale
        itemView.scaleY = itemViewInfo.scale
    }

    /*** 获取固定尺寸*/
    private fun measureChildWithExactSize(itemView: View) {
        val lp = itemView.layoutParams as RecyclerView.LayoutParams
        val widthSpec = View.MeasureSpec.makeMeasureSpec(
            mItemViewWidth - lp.leftMargin - lp.rightMargin,
            View.MeasureSpec.EXACTLY
        )
        val heightSpec = View.MeasureSpec.makeMeasureSpec(
            mItemViewHeight - lp.topMargin - lp.bottomMargin,
            View.MeasureSpec.EXACTLY
        )

        itemView.measure(widthSpec, heightSpec)
    }

    //将位置转换
    private fun convertPosition(index: Int): Int {
        return mItemCount - index - 1
    }

    //计算水平最大滑动距离
    private fun makeScrollOffsetWithRange(scrollOffset: Int): Int {
        return min(max(mItemViewWidth, scrollOffset), mItemCount * mItemViewWidth)
    }

    /*** 根据目标位置计算滑动距离*/
    fun calculateDistanceToPosition(desIndex: Int): Int {
        val preOffset = mItemViewWidth * (convertPosition(desIndex) + 1)
        return preOffset - mScrollOffset
    }

    /*** 获取每次固定的滑动位置*/
    fun getFixedScrollPosition(): Int {
        if (mHasChild) {
            if (0 == mScrollOffset % mItemViewWidth) {
                return RecyclerView.NO_POSITION
            }

            val position = mScrollOffset * 1.0f / mItemViewWidth
            return convertPosition((position - 0.5f).toInt())
        }

        return RecyclerView.NO_POSITION
    }


}