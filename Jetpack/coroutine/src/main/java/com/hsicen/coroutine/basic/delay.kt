package com.hsicen.coroutine.basic

import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


/**
 * @author: hsicen
 * @date: 2022/3/30 16:54
 * @email: codinghuang@163.com
 * description: delay
 */

private val executor = Executors.newScheduledThreadPool(1) {
  Thread(it).apply {
    isDaemon = true
  }
}

suspend fun delay(time: Long, unit: TimeUnit = TimeUnit.MILLISECONDS) {
  if (time <= 0) return
  suspendCoroutine<Unit> {
    executor.schedule({
      it.resume(Unit)
    }, time, unit)
  }
}

fun main() {
  println("start")
  runBlocking {
    println("Hello")
    delay(1000)
    println("World")
  }
  println("end.")
}