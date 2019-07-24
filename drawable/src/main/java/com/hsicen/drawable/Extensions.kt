package com.hsicen.drawable

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.util.TypedValue

/**
 * <p>作者：Hsicen  6/22/2019 12:52 PM
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：全局扩展
 */

val Float.dp2px: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
    )

val Float.sp2px: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics
    )

fun logd(msg: String, tag: String = "hsc") {
    Log.d(tag, msg)
}

/*** resource file to Bitmap
 *  it  works for jpg and png type drawables, but it does not work for xml type drawable
 *  I guess because xml file has no specific width and height information
 * */
fun drawable2Bitmap(resId: Int): Bitmap {

    return BitmapFactory.decodeResource(Resources.getSystem(), resId)
}

/*** resource file to bitmap
 * it works for xml type drawable
 * use canvas to drawable a bitmap
 */
fun drawable2Bitmap(drawable: Drawable): Bitmap {

    if (drawable is BitmapDrawable) {
        if (drawable.bitmap != null) return drawable.bitmap
    }

    val mBitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
        Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    } else {
        Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    }

    val canvas = Canvas(mBitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)

    return mBitmap
}

/**
 *  Bitmap to drawable
 */
fun Bitmap.toDrawable() = BitmapDrawable(Resources.getSystem(), this)

/**
 *  Resize bitmap
 */
fun Bitmap.resize(width: Int, height: Int) = Bitmap.createScaledBitmap(this, width, height, true)


