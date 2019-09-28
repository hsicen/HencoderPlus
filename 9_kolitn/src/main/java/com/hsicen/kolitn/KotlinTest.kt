package com.hsicen.kolitn

import android.view.View
import android.widget.TextView
import kotlinx.coroutines.*

/**
 * <p>作者：Hsicen  2019/9/23 10:15
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */

fun main() {

    checkType<Animal>(Animal())
    checkType<Person>(Animal())
    checkType<Food>(Animal())

    //协变   只能提供数据，不能添加数据
    val tvs = listOf<TextView>()
    var views: List<View> = tvs

    //可变List不支持协变   需要添加<out View>,但是不能够添加数据
    val muTvs = mutableListOf<TextView>()
    var muViews: MutableList<out View> = muTvs;
    //muViews.add(1, View(null))

    println("当前线程：${Thread.currentThread().name}")
    CoroutineScope(Dispatchers.IO).launch {
        println("当前线程：${Thread.currentThread().name}")
    }

    runBlocking(Dispatchers.Default) {
        Thread.sleep(100)
        println("1当前线程：${Thread.currentThread().name}")
    }

    println("1会阻塞么？")


    val scope = GlobalScope.launch {
        println("2开始了")
        Thread.sleep(100)
        println("2当前线程：${Thread.currentThread().name}")
    }
    println("2会阻塞么？")
    scope.start()
    Thread.sleep(50)
    scope.cancel()
    println("2取消了")
}

inline fun <reified T> checkType(item: Any) {
    if (item is T) {
        println("""参数类型正确    $item    ${T::class}""")
    } else {
        println("""参数类型错误    $item    ${T::class}""")
    }
}


fun <T> fill(arr: Array<in T>, item: T) {

    arr[0] = item
}

/**
 * 将src1 拷贝到 src2
 */
fun <T> copy(src1: Array<out T>, src2: Array<in T>) {

    for (i in src1.indices) {
        src2[i] = src1[i]
    }
}

