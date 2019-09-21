package com.hsicen.a21_drag_nestedscroll.drag

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper

/**
 * <p>作者：Hsicen  2019/8/20 16:35
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：自定义拖拽效果
 *
 * ViewDragHelper
 */
class DragHelperGridView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private val mDragHelper by lazy {
        ViewDragHelper.create(this, HDragCallback())
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
            childView.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight)
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return mDragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDragHelper.processTouchEvent(event)
        return true
    }

    override fun computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    companion object {
        private const val COLS = 2
        private const val ROWS = 3
    }

    inner class HDragCallback : ViewDragHelper.Callback() {
        //记录下被托起的View的初始位置
        private var capturedLeft = 0
        private var capturedTop = 0

        /*** 返回true时View可以被拖起来*/
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return left
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }

        /*** View被拖起时回调*/
        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            capturedChild.elevation = elevation + 1
            capturedLeft = capturedChild.left
            capturedTop = capturedChild.top
        }

        override fun onViewDragStateChanged(state: Int) {
            if (state == ViewDragHelper.STATE_IDLE) {
                val capturedView = mDragHelper.capturedView!!
                capturedView.elevation = capturedView.elevation - 1
            }
        }

        override fun onViewPositionChanged(
            changedView: View, left: Int, top: Int,
            dx: Int, dy: Int
        ) {
            //位置改变时回调
        }

        /*** View被松开时回调*/
        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            mDragHelper.settleCapturedViewAt(capturedLeft, capturedTop)
            //更新下一帧的绘制
            postInvalidateOnAnimation()
        }
    }
}
