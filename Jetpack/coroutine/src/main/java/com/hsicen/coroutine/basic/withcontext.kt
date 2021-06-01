package com.hsicen.coroutine.basic

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * 作者：hsicen  6/1/21 16:44
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

fun main() {
  runBlocking {
    println("A" + Thread.currentThread().name)
    val result = withContext(Dispatchers.Default) {
      println("B" + Thread.currentThread().name)
      delay(500)
      10 + 5
    }
    println("C" + Thread.currentThread().name)
    println("D $result " + Thread.currentThread().name)
    println("E" + Thread.currentThread().name)
  }
}

suspend fun test() {
  withContext(Dispatchers.IO) {

  }
}

