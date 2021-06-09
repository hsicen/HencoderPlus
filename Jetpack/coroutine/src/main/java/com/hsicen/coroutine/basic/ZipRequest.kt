package com.hsicen.coroutine.basic

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * 作者：hsicen  6/9/21 17:32
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

fun main(): Unit = runBlocking {
  launch(Dispatchers.IO) {
    val cost = measureTimeMillis {
      val request1 = async { requestData1() }
      val request2 = async { requestData2() }

      println(request1.await() + " ${System.currentTimeMillis()}")
      println(request2.await() + " ${System.currentTimeMillis()}")
    }
    println("cost time:$cost  ${System.currentTimeMillis()}")
  }
}

suspend fun requestData1(): String {
  delay(14000)
  return "Data from request one"
}

suspend fun requestData2(): String {
  delay(2000)
  return "Data from request two"
}
