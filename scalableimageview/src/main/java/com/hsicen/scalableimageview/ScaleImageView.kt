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
) : View(context, attrs, defStyleAttr), GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener, Runnable {

    private val mGestureDetector: GestureDetectorCompat = GestureDetectorCompat(context, this)
    private val mBitmapWidth = 250f.dp2px
    private val mBitmap = getBitmap(mBitmapWidth.toInt())
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

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
    //惯性滑动处理
    private val mScroller = OverScroller(context)

    //双击事件处理
    override fun onDoubleTap(e: MotionEvent?): Boolean {
        isScale = if (isScale) {
            mAnimator.reverse()
            false
        } else {
            mAnimator.start()
            true
        }

        return false
    }

    //双击按下后手指不离开屏幕回调此方法
    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {

        return false
    }

    //单击事件回调此方法
    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {

        return false
    }

    //按下100ms后回调此方法
    override fun onShowPress(e: MotionEvent?) {

    }

    //长按600ms后回调此方法
    override fun onLongPress(e: MotionEvent?) {

    }

    //单击抬起手指后回调此方法
    override fun onSingleTapUp(e: MotionEvent?): Boolean {

        return false
    }

    //每次按下手指调用此方法
    override fun onDown(e: MotionEvent?): Boolean {

        //返回true，确认接管每次事件
        return true
    }

    //按下后迅速抬起调用此方法
    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        //e1  该事件序列的按下事件
        //e2   触发滑动事件的当前回调事件
        //velocityX  当前x轴的速度(单位s)
        //velocityY   当前y轴的速度(单位s)

        if (isScale) {
            mScroller.fling(
                offsetX.toInt(), offsetY.toInt(), velocityX.toInt(), velocityY.toInt(),
                -(mBitmap.width * mLargeScale - width).toInt() / 2,
                (mBitmap.width * mLargeScale - width).toInt() / 2,
                -(mBitmap.height * mLargeScale - height).toInt() / 2,
                (mBitmap.height * mLargeScale - height).toInt() / 2
            )

            postOnAnimation(this)
        }

        return false
    }

    override fun run() {
        if (mScroller.computeScrollOffset()) {
            //刷新位置
            offsetX = mScroller.currX.toFloat()
            offsetY = mScroller.currY.toFloat()
            invalidate()

            postOnAnimation(this)
        }
    }

    //按下滑动调用此方法
    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        //记录按下的位置
        if (isScale) {
            offsetX -= distanceX
            offsetY -= distanceY
        }
        return false
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mSmallScale = width / mBitmap.width.toFloat()
        mLargeScale = height / mBitmap.height.toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //滑动偏移
        canvas.translate(offsetX * mFraction, offsetY * mFraction)

        //缩放
        val scale = mSmallScale + (mLargeScale - mSmallScale) * mFraction
        canvas.scale(scale, scale, width / 2f, height / 2f)
        //初始偏移到中心
        canvas.translate((width - mBitmapWidth) / 2f, (height - mBitmapWidth) / 2f)
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
}