package com.hsicen.coroutine.basic

import kotlinx.coroutines.*

/**
 * 作者：hsicen  6/1/21 16:13
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
fun main() {
    val projectJob = Job()
    val scope = CoroutineScope(projectJob + Dispatchers.Unconfined)

    println(Thread.currentThread().name)
    val launchJob = scope.launch {
        supervisorScope {
            println(Thread.currentThread().name)
            println("C")
        }
        println(Thread.currentThread().name)
        println("A")
    }

    println("B")
    //projectJob.cancel()
}