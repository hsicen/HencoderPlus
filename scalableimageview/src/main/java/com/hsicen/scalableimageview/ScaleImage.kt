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
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import kotlin.math.max
import kotlin.math.min

/**
 * <p>作者：Hsicen  2019/8/2 14:34
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：自定义双向缩放的ImageView
 */
class ScaleImage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), Runnable {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var mBitmap: Bitmap

    //偏移
    private var transX = 0f
    private var transY = 0f
    private var offsetX = 0f
    private var offsetY = 0f

    //缩放比例
    private val OVER_SCALE = 1.5f
    private var smallScale = 0f
    private var largeScale = 0f
    private var isScale = false

    //缩放
    private var mFraction = 0f
        set(value) {
            field = value
            invalidate()
        }
    private val mAnimator by lazy {
        ObjectAnimator.ofFloat(this, "mFraction", 0f, 1f)
    }

    private val mScaleDetector by lazy {ObjectAnimator.ofFloat(this, "mFraction", 0f, 1f)
        ScaleGestureDetector(context, HScaleListener())
    }

    //滑动
    private val mScroller by lazy { OverScroller(context) }
    private val mGestureDetector =
        GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent?): Boolean {

                return true
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                if (isScale) {
                    mAnimator.reverse()
                    isScale = false
                } else {
                    //缩放后手指点击的位置
                    offsetX = (e.x - width / 2f) * (1 - largeScale / smallScale)
                    offsetY = (e.y - height / 2f) * (1 - largeScale / smallScale)

                    fixPosition()

                    mAnimator.start()
                    isScale = true
                }

                return false
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                if (isScale) {
                    //记录按下位置并刷新
                    offsetX -= distanceX
                    offsetY -= distanceY

                    fixPosition()
                    invalidate()
                }

                return false
            }

            /*** 滑动位置修正*/
            private fun fixPosition() {
                offsetX = min(offsetX, (mBitmap.width * largeScale - width) / 2)
                offsetX = max(offsetX, -(mBitmap.width * largeScale - width) / 2)

                offsetY = min(offsetY, (mBitmap.height * largeScale - height) / 2)
                offsetY = max(offsetY, -(mBitmap.height * largeScale - height) / 2)
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                if (isScale) {
                    mScroller.fling(
                        offsetX.toInt(), offsetY.toInt(), velocityX.toInt(), velocityY.toInt(),
                        -((mBitmap.width * largeScale - width) / 2).toInt(),
                        ((mBitmap.width * largeScale - width) / 2).toInt(),
                        -((mBitmap.height * largeScale - height) / 2).toInt(),
                        ((mBitmap.height * largeScale - height) / 2).toInt()
                    )

                    //主线程更新UI
                    postOnAnimation(this@ScaleImage)
                }

                return false
            }
        })

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScaleImage)
        val mResourceId =
            typedArray.getResourceId(R.styleable.ScaleImage_src, 0)
        typedArray.recycle()
        mBitmap = BitmapFactory.decodeResource(resources, mResourceId)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        transX = (width - mBitmap.width) / 2f
        transY = (height - mBitmap.height) / 2f

        //根据图片尺寸，获取对应的缩放比例
        if (mBitmap.width / mBitmap.height > width / height) {
            //胖图片 按照宽度缩放
            smallScale = width / mBitmap.width.toFloat()
            largeScale = height / mBitmap.height.toFloat() * OVER_SCALE
        } else {
            //瘦图片 按长宽度缩放
            smallScale = height / mBitmap.height.toFloat()
            largeScale = width / mBitmap.width.toFloat() * OVER_SCALE
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //滑动偏移
        canvas.translate(offsetX * mFraction, offsetY * mFraction)

        //计算缩放比例
        val scale = smallScale + (largeScale - smallScale) * mFraction

        //缩放
        canvas.scale(scale, scale, width / 2f, height / 2f)
        //平移到中心
        canvas.translate(transX, transY)
        canvas.drawBitmap(mBitmap, 0f, 0f, mPaint)
    }

    override fun run() {
        //判断是否滑动中  滑动位置更新
        if (mScroller.computeScrollOffset()) {
            offsetX = mScroller.currX.toFloat()
            offsetY = mScroller.currY.toFloat()

            invalidate()
            postOnAnimation(this)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        return mGestureDetector.onTouchEvent(event)
    }

    /**
     * 双指缩放监听器
     */
    inner class HScaleListener : ScaleGestureDetector.OnScaleGestureListener {

        /*** 开始缩放回调监听*/
        override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
            //可以做一些准备工作  必须返回true后面的事件才会回调


            return true
        }

        /*** 缩放回调监听*/
        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            //具体缩放逻辑处理

        }

        /*** 缩放结束回调监听*/
        override fun onScaleEnd(detector: ScaleGestureDetector?) {
            //做一些收尾工作


        }
    }

}