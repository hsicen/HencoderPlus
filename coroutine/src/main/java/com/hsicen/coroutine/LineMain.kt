package com.hsicen.coroutine

import kotlin.concurrent.thread

/**
 * 作者：hsicen  2020/9/20 11:04
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
fun hello(postAction: () -> Unit) {

  println("Hello !!!")

  thread {
    postAction()
  }
}

fun main() {
  hello {
    println("Bye !!!")
    return@hello
  }

  println("In the end.")
}
