package com.hsicen.coroutine

/**
 * 作者：hsicen  5/5/21 6:02 PM
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */


val sequence = sequence {
  yield(1)
  yield(2)
  yield(3)
  yield(4)
  yieldAll(listOf(1, 2, 3, 4, 5))
}

val fibonacci = sequence {
  yield(1L)   //first number
  var cur = 1L
  var next = 1L

  while (true) {
    yield(next) //next number

    next += cur //next = cur + next
    cur = next - cur //cur = next
  }
}

fun main() {
  for (item in sequence) {
    println(item)
  }

  println("======分割线======")

  fibonacci.take(10).forEach(::println)
}