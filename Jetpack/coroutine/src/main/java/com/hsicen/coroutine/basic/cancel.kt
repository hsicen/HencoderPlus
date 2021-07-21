package com.hsicen.coroutine.basic

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 作者：hsicen  6/10/21 15:41
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
fun main(args: Array<String>) = runBlocking<Unit> {
    val job = launch(Dispatchers.Default) {
        var i = 0
        while (true) {
            println("Hello ${i++}")
            delay(500)
        }
    }
    delay(1000L)
    println("Cancel!")
    job.cancel()
    job.join()
    println("Done!")
}