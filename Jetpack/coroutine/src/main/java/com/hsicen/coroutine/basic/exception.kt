package com.hsicen.coroutine.basic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 作者：hsicen  6/28/21 14:24
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
fun main() = runBlocking {
    catchException1()
}

private fun catchException1() {
    val scope = CoroutineScope(Job())
    scope.launch {
        println(1 / 0)
    }
}