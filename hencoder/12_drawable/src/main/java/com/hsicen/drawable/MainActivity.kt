package com.hsicen.drawable

import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.drawable.databinding.ActivityMainBinding


/**
 * 作者：hsicen  2020/5/25 8:30
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：Bitmap和Drawable相关知识点
 *
 * Bitmap是位图信息，Drawable是绘图规则
 *
 * Drawable目录 mdpi hdpi xhdpi xxhdpi xxxhdpi
 * Density大小   1   1.5   2     3      4
 * Density dpi  160  240  320  480    640
 *
 * 图片大小的计算：
 *  首先会计算出scale值，scale = 当前手机的pdi/最接近的图片目录dpi
 *  算出图片缩放的宽高：宽 = 原宽*scale  高 = 原高*scale
 *  计算出图片大小：size = 宽 * 高 * 每一位大小(默认以为占4个字节Byte ARGB_8888)
 *
 * 但是Assets目录中的图片不会进行缩放操作，原图片的宽高是多少，解析出来的宽高就是多少
 *
 *
 *Bitmap的加载优化：
 * 1.修改Bitmap的Config：
 *  Bitmap.Config.ARGB_8888 一个像素占用4个字节
 *  Bitmap.Config.RGB_565 一个像素占用2个字节
 *
 * 2. inSampleSize:修改采样频率
 *  inSampleSize = 2
 *
 * 3.Bitmap对象复用(防止频繁的内存申请和释放造成的内存抖动)
 *  options.inMutable = true
 *  options.inBitmap = reUseBitmap
 *
 *  4.Bitmap分块显示
 *   BitmapRegionDecoder#decodeRegion
 */
class MainActivity : AppCompatActivity() {
  private lateinit var reUseBitmap: Bitmap
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnBitmap.setOnClickListener {
      throwRepeat()
    }

    reUseBitmap = BitmapFactory.decodeResource(resources, R.mipmap.hsicen)
  }

  private fun getBitmapFromAssets(): Bitmap {
    val assetManager = resources.assets
    val stream = assetManager.open("icon_water.png")
    val decodeBitmap = BitmapFactory.decodeStream(stream)
    stream.close()

    return decodeBitmap
  }

  private fun getBitmap(width: Int): Bitmap {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(resources, R.mipmap.hsicen, options)
    options.inJustDecodeBounds = false
    options.inDensity = options.outWidth
    options.inTargetDensity = width

    options.inMutable = true
    options.inBitmap = reUseBitmap


    val inStream = assets.open("assets path")
    val decoder = BitmapRegionDecoder.newInstance(inStream, false)
    val decodeBitmap = decoder?.decodeRegion(Rect(0, 0, 200, 200), options)

    return BitmapFactory.decodeResource(resources, R.mipmap.hsicen, options)
  }

  private fun getBitmap() {
    val options = BitmapFactory.Options()
    options.inPreferredConfig = Bitmap.Config.RGB_565 //一个像素占用2个字节
    options.inSampleSize = 2 //宽和高会每个2个像素采集一个点，宽和高会缩小2倍

    val mBitmap = BitmapFactory.decodeResource(resources, R.mipmap.hsicen, options)
    val byteCount = mBitmap.allocationByteCount
    println("width * height：${mBitmap.width}*${mBitmap.height}")
    println("allocationByteCount：$byteCount")
  }

  /*** resource file to Bitmap
   *  it  works for jpg and png type drawables, but it does not work for xml type drawable
   *  I guess because xml file has no specific width and height information
   * */
  fun drawable2Bitmap(resId: Int) = BitmapFactory.decodeResource(Resources.getSystem(), resId)

  /*** resource file to bitmap
   * it works for xml type drawable
   * use canvas to drawable a bitmap
   *
   * create a bitmap and canvas, and then use drawable to draw canvas
   */
  fun drawable2Bitmap(drawable: Drawable): Bitmap {

    if (drawable is BitmapDrawable) {
      if (drawable.bitmap != null) return drawable.bitmap
    }

    val mBitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
      Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    } else {
      Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
      )
    }

    val canvas = Canvas(mBitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    return mBitmap
  }


  private fun throwRepeat() {
    val arrayList = ArrayList<User>()
    arrayList.apply {
      add(User(1, "1"))
      add(User(1, "1"))
      add(User(2, "1"))
      add(User(2, "1"))
      add(User(2, "1"))
      add(User(3, "1"))
      add(User(3, "1"))
      add(User(3, "1"))
      add(User(4, "1"))
      add(User(4, "1"))
      add(User(4, ""))
    }

    val distinctBy = arrayList.distinctBy { it.id }
    println("Result：$distinctBy")
  }

  private fun reUseBitmap(): Bitmap {
    val options = BitmapFactory.Options()
    options.inPreferredConfig = Bitmap.Config.RGB_565
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(resources, R.mipmap.hsicen, options)

    options.inJustDecodeBounds = false
    if (canUseBitmap(reUseBitmap, options)) {
      options.inMutable = true
      options.inBitmap = reUseBitmap
    }

    return BitmapFactory.decodeResource(resources, R.mipmap.hsicen, options)
  }

  //判断Bitmap是否可以复用，复用条件为 reUseBitmap分配的内存大小 > options的输出Bitmap大小
  private fun canUseBitmap(reUseBitmap: Bitmap, options: BitmapFactory.Options): Boolean {


    return false
  }
}
