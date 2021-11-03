package com.aicoder.hencoderplus

/**
 * <p>作者：Hsicen  6/26/2019 3:26 PM
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
data class Purchase(val currency: String, val price: Float)

fun main() {
  val iPhone8 = Purchase("CNY", 5556f)
  val iPhoneX = iPhone8.copy(price = 9999f)

  println("iPhone8:   $iPhone8")
  println("iPhoneX:   $iPhoneX")
}