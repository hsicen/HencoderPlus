package com.hsicen.multitouch

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * <p>作者：Hsicen  2019/8/5 15:54
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：多点触控
 */
class MultiTouchTestView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mBitmapWidth = 250f.dp2px
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mBitmap = getBitmap(mBitmapWidth.toInt())

    //手指偏移位置
    private var offsetX = 0f
    private var offsetY = 0f

    //手指按下的位置
    private var downX = 0f
    private var downY = 0f

    //手指按下时View的位置
    private var viewX = 0f
    private var viewY = 0f

    //当前按下手指id
    private var trackPointerId = 0

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        offsetX = (width - mBitmapWidth) / 2
        offsetY = (height - mBitmapWidth) / 2
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(mBitmap, offsetX, offsetY, mPaint)
    }

    /**
     * 接力型多点触控处理
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                //记录手指按下位置
                val currentIndex = event.actionIndex
                trackPointerId = event.getPointerId(currentIndex)
                downX = event.getX(currentIndex)
                downY = event.getY(currentIndex)

                //记录View偏移位置
                viewX = offsetX
                viewY = offsetY
            }

            MotionEvent.ACTION_MOVE -> {
                //更新偏移  重绘View
                val currentIndex = event.findPointerIndex(trackPointerId)
                offsetX = viewX + event.getX(currentIndex) - downX
                offsetY = viewY + event.getY(currentIndex) - downY
                invalidate()
            }

            MotionEvent.ACTION_POINTER_DOWN -> {
                //记录手指按下位置
                val currentIndex = event.actionIndex
                trackPointerId = event.getPointerId(currentIndex)
                downX = event.getX(currentIndex)
                downY = event.getY(currentIndex)

                //记录View偏移位置
                viewX = offsetX
                viewY = offsetY
            }

            MotionEvent.ACTION_POINTER_UP -> {
                //判断抬起手指位置
                var currentIndex = event.actionIndex
                val currentId = event.getPointerId(currentIndex)

                if (currentId == trackPointerId) {

                    //更新当前View由哪个手指控制
                    currentIndex = if (currentIndex == event.pointerCount - 1) {
                        event.pointerCount - 2
                    } else event.pointerCount - 1

                    trackPointerId = event.getPointerId(currentIndex)
                    downX = event.getX(currentIndex)
                    downY = event.getY(currentIndex)

                    //记录View偏移位置
                    viewX = offsetX
                    viewY = offsetY
                }
            }
        }

        return true
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