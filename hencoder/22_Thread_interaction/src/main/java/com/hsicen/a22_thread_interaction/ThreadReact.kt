package com.hsicen.a22_thread_interaction

/**
 * 作者：hsicen  2020/4/13 18:39
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

fun main() {
    //threadLaunch()

    //threadInterrupt()

    threadJoin()
}

fun threadLaunch() {
    println("启动线程前： ${Thread.currentThread().name}")

    val mt = Thread(Runnable {
        println("启动线程内： ${Thread.currentThread().name}")
        Thread.sleep(2000)
        println("线程结束： ${Thread.currentThread().name}")
    })

    mt.start()
    println("启动线程后：${Thread.currentThread().name}")
    Thread.sleep(1000)
}


fun threadInterrupt() {
    println("启动线程前： ${Thread.currentThread().name}")

    val mt = Thread {
        println("启动线程内： ${Thread.currentThread().name}")
        var count = 0
        Thread.currentThread().isInterrupted
        while (!Thread.interrupted()) {
            println("线程在运行：${count++}")
        }
    }

    mt.start()
    mt.join()
    println("启动线程后：${Thread.currentThread().name}")
    Thread.sleep(5)
    mt.interrupt()
}

var helloStr: String? = null


fun printStr() {
    println("The content is $helloStr")
}

@Synchronized
fun initStr() {
    while (null == helloStr) {
        waitNotify()
    }

    helloStr = "Hello World"
}


fun waitNotify() {
    val thread1 = Thread {
        Thread.sleep(15000)
        initStr()
    }

    val thread2 = Thread {
        Thread.sleep(300)
        printStr()
    }

    thread1.start()
    thread2.start()
}


fun threadJoin() {
    val thread1 = Thread {
        Thread.sleep(15000)
        initStr()
    }

    val thread2 = Thread {
        Thread.sleep(300)
        thread1.join()
        printStr()
    }

    thread1.start()
    thread2.start()
}