package com.hsicen.a22_thread.kotlin

import kotlinx.coroutines.*

/**
 * 作者：hsicen  2020/4/13 14:51
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：Kotlin协程测试
 */

fun main() {
    /*println("=====测试runBlock=====")
    printMemory("方法前")
    testRunBlock()
    System.gc()
    printMemory("方法后")

    println()
    println("=====测试GlobalScope=====")
    printMemory("方法前")
    testGlobalScope()
    System.gc()
    printMemory("方法后")

    println()
    println("=====测试CoroutineScope=====")
    testCoroutineScope()*/

    println()
    println("=====测试CoroutineScope并发=====")
    testMultiCoroutine()
}


//runBlocking为顶层函数，是线程阻塞的，默认在启动协程的线程中运行
fun testRunBlock() {
    println("方法开始运行  ${Thread.currentThread().name}")
    runBlocking(Dispatchers.IO) {
        val _10M = 10 * 1024 * 1024
        val byte = ByteArray(_10M * 8)
        System.gc()
        printMemory("方法中")
        println("进入协程内执行  ${Thread.currentThread().name}")
        delay(1000)
        println("协程执行结束  ${Thread.currentThread().name}")
    }

    println("协程外执行  ${Thread.currentThread().name}")
}

//GlobalScope为单例对象，不会线程阻塞，但生命周期和App生命周期一致且不能取消
fun testGlobalScope() = runBlocking {
    println("方法开始运行  ${Thread.currentThread().name}")
    GlobalScope.launch(Dispatchers.IO) {
        val _10M = 10 * 1024 * 1024
        val byte = ByteArray(_10M * 8)
        System.gc()
        printMemory("方法中")
        println("进入协程内执行  ${Thread.currentThread().name}")
        delay(1000)
        println("协程执行结束  ${Thread.currentThread().name}")
    }

    println("协程外执行  ${Thread.currentThread().name}")
}


//CoroutineScope，不会线程阻塞，可以取消
fun testCoroutineScope() {
    println("方法开始运行  ${Thread.currentThread().name}")
    val cs = CoroutineScope(Dispatchers.IO).launch {
        println("进入协程内执行  ${Thread.currentThread().name}")
        delay(1000)
        println("协程执行结束  ${Thread.currentThread().name}")
    }

    //cs.cancel(CancellationException("手动取消"))
    println("协程外执行  ${Thread.currentThread().name}")
}


//协程的并发请求
fun testMultiCoroutine() = runBlocking {
    CoroutineScope(Dispatchers.IO).launch {
        val a = async {
            delay(500)
            3
        }

        val b = async {
            delay(1000)
            10
        }

        println("The result is ${a.await() + b.await()}")
        //println("会在结果出来前执行么？")
    }

    println("会在结果出来前执行么？")
    delay(1500)
}


fun printMemory(str: String) {
    println(
        "$str     free is ${Runtime.getRuntime()
            .freeMemory() / 1024 / 1024} M，total is ${Runtime.getRuntime()
            .totalMemory() / 1024 / 1024} M"
    )
}
