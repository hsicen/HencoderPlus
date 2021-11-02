package com.hsicen.coroutine.basic

import kotlinx.coroutines.*

/**
 * 作者：hsicen  6/10/21 17:29
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：SupervisorJob
 */

fun main(args: Array<String>): Unit = runBlocking {

  val uiScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
  val childJob1 = uiScope.launch {

  }

  val childJob2 = uiScope.launch {

  }

  val childJob3 = uiScope.launch {

  }
}