package com.hsicen.coroutine.basic

import kotlinx.coroutines.*

/**
 * 作者：hsicen  6/10/21 16:31
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：Coroutine - Job
 */
fun main(args: Array<String>) = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) {
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("Hello ${i++}")
                nextPrintTime += 500L
            }
        }
    }
    delay(1000L)
    println("Cancel!")
    job.cancel()
    println("Done!")
}

suspend fun work() {
    val startTime = System.currentTimeMillis()
    var nextPrintTime = startTime
    var i = 0
    while (i < 5) {
        yield()
        // print a message twice a second
        if (System.currentTimeMillis() >= nextPrintTime) {
            println("Hello ${i++}")
            nextPrintTime += 500L
        }
    }
}