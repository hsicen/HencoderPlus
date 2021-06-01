package com.hsicen.coroutine.basic

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
    measureTimeMillis {
      doSomethingUsefulOne()
      doSomethingUsefulTwo()
    }
  }
}