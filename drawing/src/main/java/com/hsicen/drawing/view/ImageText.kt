package com.hsicen.drawing.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hsicen.drawing.R
import com.hsicen.drawing.dp2px
import com.hsicen.drawing.sp2px

/**
 * <p>作者：Hsicen  6/25/2019 4:31 PM
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：支持图文混排的View
 *
 *   使用StaticLayout进行多行文字测量
 */
class ImageText @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    private val mWidth = 150f.dp2px
    private val mPadding = 100f.dp2px
    private val mBitmap = getBitmap(mWidth.toInt())
    private val mMetrics = Paint.FontMetrics()
    private val mMeasureWidth = FloatArray(1)


    private val mText = "管理员在后台可设置各期开奖奖品，设置限时抽奖是否开启，查看各期开奖中奖情况啊" +
            "派发奖品后可填写物流信息以供用户在前台APP页面中查看管理员在后台可设置各期开奖奖品，设置限" +
            "时抽奖是否开启，查看各期开奖中奖情况，派发奖品后可填写物流信息以供用户在前台APP页面中查看" +
            "管理员在后台可设置各期开奖奖品，设置限时抽奖是否开启，查看各期开奖中奖情况，派发奖品后可填" +
            "写物流信息以供用户在前台APP页面中查看"

    private val mPaint by lazy {
        Paint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //绘制图片
        canvas.drawBitmap(mBitmap, width - mBitmap.width - 5f.dp2px, mPadding, mPaint)

        //绘制文字
        mPaint.textSize = 20f.sp2px
        mPaint.color = Color.WHITE
        mPaint.getFontMetrics(mMetrics)

        var start = 0
        val length = mText.length
        var count = 0
        var offset = -mMetrics.top + 3f.dp2px
        var mMaxWidth: Float

        while (start < length) {
            start += count

            mMaxWidth = if (offset > mPadding && offset < mPadding + mWidth - mMetrics.top)
                width - mWidth - 5f.dp2px else width.toFloat() - 5f.dp2px

            count = mPaint.breakText(mText, start, mText.length, true, mMaxWidth, mMeasureWidth)
            canvas.drawText(mText, start, start + count, 5f.dp2px, offset, mPaint)
            offset += mPaint.fontSpacing
        }
    }

    /*** 根据指定宽度缩放图片*/
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