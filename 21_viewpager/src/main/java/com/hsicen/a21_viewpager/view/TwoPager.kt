package com.hsicen.a21_viewpager.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.OverScroller
import kotlin.math.abs

/**
 * <p>作者：Hsicen  2019/8/19 16:02
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：手写ViewPager
 */
class TwoPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private var downX = 0f
    private var downY = 0f
    private var downScrollX = 0f
    private var scrolling = false
    private var minVelocity: Int
    private var maxVelocity: Int

    private val overScroller = OverScroller(context)
    private val viewConfiguration = ViewConfiguration.get(context)
    private val velocityTracker = VelocityTracker.obtain()

    init {
        minVelocity = viewConfiguration.scaledMinimumFlingVelocity
        maxVelocity = viewConfiguration.scaledMaximumFlingVelocity
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        var childLeft = 0
        val childTop = 0
        var childRight = width
        val childBottom = height

        for (pos in 0 until childCount) {
            val childView = getChildAt(pos)
            childView.layout(childLeft, childTop, childRight, childBottom)
            childLeft += width
            childRight += width
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (ev.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }

        velocityTracker.addMovement(ev)
        var result = false
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                scrolling = false
                downX = ev.x
                downY = ev.y

                downScrollX = scrollX.toFloat()
            }

            MotionEvent.ACTION_MOVE -> {
                if (!scrolling) {
                    val dx = downX - ev.x
                    //判断是否达到滑动状态的临界值
                    if (abs(dx) > viewConfiguration.scaledPagingTouchSlop) {
                        scrolling = true
                        parent.requestDisallowInterceptTouchEvent(true)
                        result = true
                    }
                }
            }
        }

        return result
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }

        velocityTracker.addMovement(event)

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                downScrollX = scrollX.toFloat()
            }

            MotionEvent.ACTION_MOVE -> {
                var dx = downX - event.x + downScrollX
                if (dx > width) {
                    dx = width.toFloat()
                } else if (dx < 0) dx = 0f

                scrollTo(dx.toInt(), 0)
            }

            MotionEvent.ACTION_UP -> {
                //滑动惯性处理
                velocityTracker.computeCurrentVelocity(1000, maxVelocity.toFloat())
                val xVelocity = velocityTracker.xVelocity

                var targetPage = 0
                targetPage = if (abs(xVelocity) < minVelocity) {
                    if (scrollX > width / 2) 1 else 0
                } else {
                    if (xVelocity < 0) 1 else 0
                }

                val scrollDistance = if (targetPage == 1) (width - scrollX) else -scrollX
                overScroller.startScroll(scrollX, 0, scrollDistance, 0)
                postInvalidateOnAnimation()
            }
        }

        return true
    }

    override fun computeScroll() {
        if (overScroller.computeScrollOffset()) {
            scrollTo(overScroller.currX, overScroller.currY)
            postInvalidateOnAnimation()
        }
    }
}