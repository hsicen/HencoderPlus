package com.hsicen.coroutine.basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 作者：hsicen  6/1/21 17:40
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

fun simple(): Flow<Int> = flow { // flow builder
  for (i in 1..3) {
    delay(100) // pretend we are doing something useful here
    emit(i) // emit next value
  }
}

fun main(): Unit = runBlocking {
  // Launch a concurrent coroutine to check if the main thread is blocked
  launch {
    for (k in 1..3) {
      println("I'm not blocked $k")
      delay(100)
    }
  }
}
