package com.hsicen.drawing.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hsicen.drawing.R
import com.hsicen.drawing.dp2px

/**
 * <p>作者：Hsicen  2019/7/9 7:51
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：使用Camera做三维变换
 */
class CameraView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mWidth = 250f.dp2px
    private val mCamera = Camera()

    private var mRotate = 30f
    private var mFlipRotate = 20f

    private val mPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    private val mBitmap by lazy {
        getBitmap(mWidth.toInt())
    }

    init {
        mCamera.rotateX(45f)
        mCamera.setLocation(0f, 0f, -8 * (context.resources.displayMetrics.density))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //绘制上半部分
        canvas.save()
        canvas.translate(width / 2f, height / 2f)
        canvas.rotate(-mFlipRotate)
        canvas.clipRect(-mWidth, -mWidth, mWidth, 0f)
        canvas.rotate(mFlipRotate)
        canvas.translate(-width / 2f, -height / 2f)
        canvas.drawBitmap(mBitmap, width / 2f - mWidth / 2, height / 2f - mWidth / 2f, mPaint)
        canvas.restore()

        //绘制下半部分
        canvas.save()
        canvas.translate(width / 2f, height / 2f)
        canvas.rotate(-mFlipRotate)
        mCamera.applyToCanvas(canvas)
        canvas.clipRect(-mWidth, 0f, mWidth, mWidth)
        canvas.rotate(mFlipRotate)
        canvas.translate(-width / 2f, -height / 2f)
        canvas.drawBitmap(mBitmap, width / 2f - mWidth / 2, height / 2f - mWidth / 2f, mPaint)
        canvas.restore()
    }

    private fun getBitmap(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.hsicen, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width

        return BitmapFactory.decodeResource(resources, R.drawable.hsicen, options)
    }
}