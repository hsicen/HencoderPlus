package com.hsicen.coroutine.basic

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * 作者：hsicen  6/1/21 17:14
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

suspend fun doSomethingUsefulOne(): Int {
  delay(1000L) // pretend we are doing something useful here
  return 13
}

suspend fun doSomethingUsefulTwo(): Int {
  delay(1000L) // pretend we are doing something useful here, too
  return 29
}

fun main() {
  runBlocking {
    println("耗时: " + measureTimeMillis {
      doSomethingUsefulOne()
      doSomethingUsefulTwo()
    })

    println("======分割线======")

    println("耗时: " + measureTimeMillis {
      val async = async { doSomethingUsefulOne() }
      val async1 = async { doSomethingUsefulTwo() }

      async.await()
      async1.await()
    })
  }
}