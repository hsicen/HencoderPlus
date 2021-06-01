package com.hsicen.coroutine.basic

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * 作者：hsicen  5/31/21 17:51
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

fun main() {
  test1()
}

private fun test1() {
  runBlocking {
    println("runBlocking start")
    coroutineScope {
      println("coroutineScope start")

      repeat(5) {
        println("$it")
      }
    }

    println("coroutineScope finish")
  }
  println("runBlocking finish")
}


@ExperimentalContracts
fun executeOnce(block: () -> Unit) {
  contract {
    callsInPlace(block, InvocationKind.EXACTLY_ONCE)
  }
}

@ExperimentalContracts
fun caller() {
  val value: String
  executeOnce {
    value = "dummy-string"
  }
}