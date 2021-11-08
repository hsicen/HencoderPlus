package com.hsicen.scalableimageview

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import kotlin.math.max
import kotlin.math.min

/**
 * <p>作者：Hsicen  2019/7/30 0:34
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：双向缩放的ImageView
 */
class ScaleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mGestureListener = HGestureListener()
    private val mGestureDetector: GestureDetectorCompat =
        GestureDetectorCompat(context, mGestureListener)
    private val mBitmapWidth = 250f.dp2px
    private val mBitmap = getBitmap(mBitmapWidth.toInt())
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mOverScale = 1.3f

    private var transX = 0f
    private var transY = 0f

    private var offsetY = 0f
    private var offsetX = 0f

    private var isScale = false
    private var mSmallScale = 0f
    private var mLargeScale = 0f

    //记录动画完成度
    private var mFraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    //缩放动画
    private val mAnimator by lazy {
        ObjectAnimator.ofFloat(this, "mFraction", 0f, 1f)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        transX = (width - mBitmapWidth) / 2f
        transY = (height - mBitmapWidth) / 2f

        if (mBitmap.width.toFloat() / mBitmap.height > width.toFloat() / height) {
            //比较胖的图片
            mSmallScale = width / mBitmap.width.toFloat()
            mLargeScale = height * mOverScale / mBitmap.height.toFloat()
        } else {
            //比较修长的图片
            mSmallScale = height * mOverScale / mBitmap.height.toFloat()
            mLargeScale = width / mBitmap.width.toFloat()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //滑动偏移
        canvas.translate(offsetX * mFraction, offsetY * mFraction)

        //缩放
        val scale = mSmallScale + (mLargeScale - mSmallScale) * mFraction
        canvas.scale(scale, scale, width / 2f, height / 2f)
        //初始偏移到中心
        canvas.translate(transX, transY)
        canvas.drawBitmap(mBitmap, 0f, 0f, mPaint)
    }

    //让GestureDetector接管事件的分发
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return mGestureDetector.onTouchEvent(event)
    }

    private fun getBitmap(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width

        return BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
    }

    /**
     * 自定义手势监听器
     */
    inner class HGestureListener : GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener, Runnable {
        //惯性滑动处理
        private val mScroller = OverScroller(context)

        override fun run() {
            //判断动画是否在进行中，快速滑动，界面更新
            if (mScroller.computeScrollOffset()) {
                //刷新位置
                offsetX = mScroller.currX.toFloat()
                offsetY = mScroller.currY.toFloat()
                invalidate()

                postOnAnimation(this)
            }
        }

        /*** 每次 ACTION_DOWN 事件出现的时候都会被调用*/
        override fun onDown(e: MotionEvent?): Boolean {

            //在这⾥里里返回 true 可以保证必然消费掉事件
            return true
        }

        /*** ⽤户按下 100ms 不松⼿后会被调用，⽤于标记「可以显示按下状态了」*/
        override fun onShowPress(e: MotionEvent?) {

        }

        /*** ⽤户单击时被调用(长按后松手不会调用、双击的第⼆下时不会被调用)*/
        override fun onSingleTapUp(e: MotionEvent?): Boolean {

            return false
        }

        /*** ⽤户长按（按下 500ms 不松手）后会被调用*/
        override fun onLongPress(e: MotionEvent?) {

        }

        /*** 按下，抬起后  惯性滑动调用此方法*/
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (isScale) {
                mScroller.fling(
                    offsetX.toInt(), offsetY.toInt(), velocityX.toInt(), velocityY.toInt(),
                    -((mBitmap.width * mLargeScale - width) / 2).toInt(),
                    ((mBitmap.width * mLargeScale - width) / 2).toInt(),
                    -((mBitmap.height * mLargeScale - height) / 2).toInt(),
                    ((mBitmap.height * mLargeScale - height) / 2).toInt()
                )

                postOnAnimation(this)
            }

            return false
        }

        /*** 按下不松开滑动调用此方法*/
        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            if (isScale) {
                //按下位置更新和滑动界面限制
                offsetX -= distanceX
                offsetY -= distanceY

                fixPosition()
                invalidate()
            }

            return false
        }

        private fun fixPosition() {
            offsetX = min(offsetX, (mBitmap.width * mLargeScale - width) / 2)
            offsetX = max(offsetX, -(mBitmap.width * mLargeScale - width) / 2)

            offsetY = min(offsetY, (mBitmap.height * mLargeScale - height) / 2)
            offsetY = max(offsetY, -(mBitmap.height * mLargeScale - height) / 2)
        }

        /*** 双击时调用此方法*/
        override fun onDoubleTap(e: MotionEvent): Boolean {
            isScale = if (isScale) {
                mAnimator.reverse()
                false
            } else {
                //设置以点击处为中心放大
                offsetX = (e.x - width / 2f) * (1 - mLargeScale / mSmallScale)
                offsetY = (e.y - height / 2f) * (1 - mLargeScale / mSmallScale)

                fixPosition()
                mAnimator.start()
                true
            }

            return false
        }

        /*** 双击后移动抬起后调用此方法*/
        override fun onDoubleTapEvent(e: MotionEvent?): Boolean {

            return false
        }

        /*** 按下抬起，并且在规定时间内不按下调用此方法*/
        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {

            return false
        }

    }
}