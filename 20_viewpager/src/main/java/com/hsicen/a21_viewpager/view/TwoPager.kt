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
 * <p>描述：ViewGroup触摸反馈算法
 *    onDispatchEvent()  事件分发
 *    onInterceptTouchEvent() 事件拦截
 *    onTouchEvent() 事件处理
 */
class TwoPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private var downX = 0f
    private var downY = 0f
    //画面初始偏移
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
        //进行子View的测量(让子View使用父View的宽高限制)   忽略了xml布局中开发者的要求
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        //进行子View的布局(位置确定)  忽略了xml布局中开发者的要求
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

    //ViewGroup 事件拦截逻辑处理
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (ev.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        velocityTracker.addMovement(ev)

        var result = false
        when (ev.actionMasked) {
            //存储初始状态   类似onTouchEvent()的ACTION_DOWN判断
            MotionEvent.ACTION_DOWN -> {
                scrolling = false

                downX = ev.x
                downY = ev.y
                downScrollX = scrollX.toFloat()
            }

            //判断是否拦截   拦截条件：用户是否滑动的足够长
            MotionEvent.ACTION_MOVE -> {
                if (!scrolling) {
                    val dx = downX - ev.x  //计算用户滑了多长
                    //判断长度是否达到滑动状态的临界值
                    if (abs(dx) > viewConfiguration.scaledPagingTouchSlop) {
                        scrolling = true  //标记为滑动状态
                        //告诉父View不要拦截当前事件，让自己的onTouchEvent()处理
                        parent.requestDisallowInterceptTouchEvent(true)
                        //拦截当前事件，不交给子View处理，自己处理
                        result = true
                    }
                }
            }
        }

        return result
    }

    //View触摸事件逻辑处理(View的滑动逻辑处理)
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        velocityTracker.addMovement(event)

        when (event.actionMasked) {
            //记录初始状态
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                downScrollX = scrollX.toFloat()
            }

            //滑动范围判断
            MotionEvent.ACTION_MOVE -> {
                var dx = downX - event.x + downScrollX
                //边缘判断
                if (dx > width) {
                    dx = width.toFloat()
                } else if (dx < 0) dx = 0f

                //从子View的第几个像素开始显示
                scrollTo(dx.toInt(), 0)
            }

            //滑动惯性处理(手指离开屏幕)
            MotionEvent.ACTION_UP -> {
                //计算滑动惯性
                velocityTracker.computeCurrentVelocity(1000, maxVelocity.toFloat())
                val xVelocity = velocityTracker.xVelocity

                val targetPage = if (abs(xVelocity) < minVelocity) {
                    if (scrollX > width / 2) 1 else 0
                } else {
                    if (xVelocity < 0) 1 else 0
                }

                val scrollDistance = if (targetPage == 1) (width - scrollX) else -scrollX
                overScroller.startScroll(scrollX, 0, scrollDistance, 0)
                //会在View的下一帧调用draw 方法
                // postInvalidateOnAnimation -> Invalidate -> draw -> computeScroll
                postInvalidateOnAnimation()
            }
        }

        return true
    }

    //draw 方法中会调用computeScroll
    override fun computeScroll() {
        if (overScroller.computeScrollOffset()) {
            scrollTo(overScroller.currX, overScroller.currY)
            postInvalidateOnAnimation()
        }
    }
}