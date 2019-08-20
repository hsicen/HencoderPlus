package com.hsicen.animator

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * <p>作者：Hsicen  2019/7/9 7:51
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：使用Camera做三维变换
 */
class CameraView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr) {

    private val mWidth = 250f.dp2px
    private val mCamera = Camera()

    var mRotate = 0f
        set(value) {
            field = value
            invalidate()
        }
    var mTopFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    var mBottomFlip = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val mPaint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }
    private val mBitmap by lazy { getBitmap(mWidth.toInt()) }

    init {
        mCamera.setLocation(0f, 0f, -8 * (context.resources.displayMetrics.density))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //绘制上半部分
        canvas.save()
        canvas.translate(width / 2f, height / 2f)
        canvas.rotate(-mRotate)
        mCamera.save()
        //旋转是增量值
        mCamera.rotateX(mTopFlip)
        mCamera.applyToCanvas(canvas)
        mCamera.restore()
        canvas.clipRect(-width / 2f, -height / 2f, width / 2f, 0f)
        canvas.rotate(mRotate)
        canvas.translate(-width / 2f, -height / 2f)
        canvas.drawBitmap(mBitmap, width / 2f - mWidth / 2, height / 2f - mWidth / 2f, mPaint)
        canvas.restore()

        //绘制下半部分
        canvas.save()
        canvas.translate(width / 2f, height / 2f)
        canvas.rotate(-mRotate)
        mCamera.save()
        //旋转是增量值
        mCamera.rotateX(mBottomFlip)
        mCamera.applyToCanvas(canvas)
        mCamera.restore()
        canvas.clipRect(-width / 2f, 0f, width / 2f, height / 2f)
        canvas.rotate(mRotate)
        canvas.translate(-width / 2f, -height / 2f)
        canvas.drawBitmap(mBitmap, width / 2f - mWidth / 2, height / 2f - mWidth / 2f, mPaint)
        canvas.restore()
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