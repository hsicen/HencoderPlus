package com.hsicen.coroutine.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 作者：hsicen  6/1/21 15:46
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

fun main() {
  GlobalScope.launch {
    delay(200)
    println("Hello")
  }

  println("World!")
}