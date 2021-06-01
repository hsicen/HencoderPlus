package com.hsicen.coroutine.basic

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * 作者：hsicen  6/1/21 15:27
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

fun main() {
  runBlocking(Dispatchers.IO) {
    println("A")
    delay(500)
    println("B")
  }

  println("C")
}