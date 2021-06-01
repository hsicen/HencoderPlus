package com.hsicen.coroutine.basic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 作者：hsicen  6/1/21 16:13
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
fun main() {
  val projectJob = Job() //默认创建一个新的线程
  val scope = CoroutineScope(projectJob)

  println(Thread.currentThread().name)
  scope.launch {//可以手动指定运行线程
    println(Thread.currentThread().name)
    println("A")
  }

  println("B")
  projectJob.cancel()
}