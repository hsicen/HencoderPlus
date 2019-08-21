package com.hsicen.a21_drag_nestedscroll.drag

import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * <p>作者：Hsicen  2019/8/20 16:36
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：使用DragListener实现拖拽效果
 *
 * OnDragListener
 */
class DragListenerGridView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private val mDragListener = HDragListener()
    private val mOrderChild = ArrayList<View>()

    private var mDragView: View? = null


    init {
        isChildrenDrawingOrderEnabled = true
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            //初始化设置
            mOrderChild.add(childView)
            childView.setOnLongClickListener {
                mDragView = it
                it.startDrag(null, DragShadowBuilder(it), it, 0)
                false
            }

            childView.setOnDragListener(mDragListener)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val specWidth = MeasureSpec.getSize(widthMeasureSpec)
        val specHeight = MeasureSpec.getSize(heightMeasureSpec)

        val childWidth = specWidth / COLS
        val childHeight = specHeight / ROWS
        measureChildren(
            MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY)
        )

        setMeasuredDimension(specWidth, specHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft: Int
        var childTop: Int
        val childWidth = width / COLS
        val childHeight = height / ROWS

        for (pos in 0 until childCount) {
            val childView = getChildAt(pos)
            childLeft = pos % 2 * childWidth
            childTop = pos / 2 * childHeight
            childView.layout(0, 0, childWidth, childHeight)
            childView.translationX = childLeft.toFloat()
            childView.translationY = childTop.toFloat()
        }
    }

    companion object {
        private const val COLS = 2
        private const val ROWS = 3
    }

    /**
     * 自定义拖拽监听器
     */
    inner class HDragListener : OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED ->
                    if (event.localState == v) v.visibility = View.INVISIBLE

                DragEvent.ACTION_DRAG_ENTERED ->
                    if (event.localState != v) sort(v)

                DragEvent.ACTION_DRAG_ENDED ->
                    if (event.localState == v) v.visibility = View.VISIBLE
            }

            return true
        }
    }

    private fun sort(targetView: View) {
        var dragIndex = -1
        var targetIndex = -1

        for (index in 0 until childCount) {
            val child = mOrderChild[index]
            if (targetView == child) {
                targetIndex = index
            } else if (mDragView == child) {
                dragIndex = index
            }
        }

        if (targetIndex < dragIndex) {
            mOrderChild.removeAt(dragIndex)
            mOrderChild.add(targetIndex, mDragView!!)
        } else if (targetIndex > dragIndex) {
            mOrderChild.removeAt(dragIndex)
            mOrderChild.add(targetIndex, mDragView!!)
        }

        var childLeft: Int
        var childTop: Int
        val childWidth = width / COLS
        val childHeight = height / ROWS
        for (pos in 0 until childCount) {
            val childView = getChildAt(pos)
            childLeft = pos % 2 * childWidth
            childTop = pos / 2 * childHeight
            childView.animate()
                .translationX(childLeft.toFloat())
                .translationY(childTop.toFloat())
                .duration = 150
        }
    }
}