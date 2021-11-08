package com.hsicen.a21_drag_nestedscroll.drag

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper
import com.hsicen.a21_drag_nestedscroll.R
import kotlin.math.abs

/**
 * <p>作者：Hsicen  2019/9/9 15:41
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：ViewGroup.ViewDragHelper的使用
 *
 * 重写onTouchEvent,onInterceptTouchEvent  开启拖拽
 *      mDragHelper.processTouchEvent()
 *      mDragHelper.shouldInterceptTouchEvent()
 *
 * 实现ViewDragHelper.Callback  设置拖拽监听
 */
class DragUpDownLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    /*** 被拖拽的View*/
    private lateinit var mChild: View
    private val mViewConfiguration by lazy {
        ViewConfiguration.get(context)
    }

    /*** 拖拽工具类*/
    private val mDragHelper by lazy {
        ViewDragHelper.create(this, DragCallback())
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        mChild = findViewById(R.id.dragView)
    }

    override fun computeScroll() {
        if (mDragHelper.continueSettling(true))
            ViewCompat.postInvalidateOnAnimation(this)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return mDragHelper.shouldInterceptTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDragHelper.processTouchEvent(event)
        return true
    }

    /*** 拖拽监听*/
    inner class DragCallback : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int) = mChild == child

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int) = top

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            if (abs(yvel) > mViewConfiguration.scaledMinimumFlingVelocity) {
                if (0 < yvel) {
                    mDragHelper.settleCapturedViewAt(0, height - releasedChild.height)
                } else mDragHelper.settleCapturedViewAt(0, 0)
            } else {
                if (releasedChild.top < height - releasedChild.bottom) {
                    mDragHelper.settleCapturedViewAt(0, 0)
                } else mDragHelper.settleCapturedViewAt(0, height - releasedChild.height)
            }

            postInvalidateOnAnimation()
        }
    }

}