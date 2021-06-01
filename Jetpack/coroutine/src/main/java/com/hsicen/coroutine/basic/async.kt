package com.hsicen.coroutine.basic

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * 作者：hsicen  6/1/21 16:35
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
fun main() {
  runBlocking {
    println("A")
    val result1 = async {
      delay(500)
      println("B")
      5 + 5
    }

    println("C")
    val result2 = async {
      delay(500)
      println("D")
      4 + 3
    }

    println("E")
    println(result1.await() + result2.await())
    println("F")
  }
}