package com.hsicen.drawing.test

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hsicen.drawing.R
import com.hsicen.drawing.dp2px

/**
 * 作者：hsicen  2020/9/13 22:04
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class UserView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val WIDTH = 200f.dp2px
    private val OFFSET = 30f.dp2px
    private val bounds = RectF(OFFSET, OFFSET, OFFSET + WIDTH, OFFSET + WIDTH)

    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private val mPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //绘制底图，形成圆环
        val radius = WIDTH / 2f
        mPaint.color = Color.parseColor("#71BB8F")
        canvas.drawCircle(OFFSET + radius, OFFSET + radius, radius + 3f.dp2px, mPaint)

        //设置离屏缓冲
        val saveLayer = canvas.saveLayer(bounds, mPaint)

        //画圆形
        canvas.drawCircle(OFFSET + radius, OFFSET + radius, radius, mPaint)

        //花图片
        mPaint.xfermode = xfermode
        canvas.drawBitmap(getBitmap(WIDTH.toInt()), OFFSET, OFFSET, mPaint)
        mPaint.xfermode = null

        //恢复离屏缓冲
        canvas.restoreToCount(saveLayer)
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