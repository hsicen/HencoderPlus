package com.hsicen.coroutine.basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine

/**
 * 作者：hsicen  6/1/21 16:22
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
fun main() {
  runBlocking {
    launch {
      delay(200)
      println("A")
    }

    launch {
      delay(100)
      println("B")
    }
  }

  println("C")
}