package com.aicoder.hencoderplus

import android.content.res.Resources
import android.util.TypedValue
import kotlin.system.measureTimeMillis

/**
 * <p>作者：Hsicen  6/13/2019 8:27 AM
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */

fun main() {

    //repeatTest(10)
    //inTest(10)
    //untilTest(10)
    //rangeToTest(10)
    //myRangeTest(10)
    //infixTest()
    //sthTest()

    numberTest()
}

private fun sthTest() {
    val data = listOf("Kotlin", "Java", "C++", "Python", "C", "Swift", "Flutter", "ReactNative")
    callbackWhatSelected(data, fun(value: String, index: Int) {
        println("你选择了$value     下标为:  $index")
    })

    val time = measureTimeMillis {
        saySelect(::sayInline, ::sayNoInline)
    }

    println("measure:   $time")
}


fun numberTest() {
    val dNum = 123.5
    val fNum = 123f
    val sNum = 1_0000_000
    val creditnum = 511322_19931107_4135

    val i = 0b1101_0010
    val result = i ushr 3
    println("Pre is  $i")
    println("The result is    $result")
}

inline fun saySelect(inlined: () -> Unit, noinline notInlined: () -> Unit) {

    inlined.invoke()
    notInlined.invoke()
}


private fun sayInline() {
    println("inline call")
}

private fun sayNoInline() {
    println("not inline call")
}

private fun infixTest() {
    val account = Account()
    println("first:  ${account.banlance}")
    account add 9750.0
    println("now:  ${account.banlance}")
}

fun repeatTest(count: Int) {
    repeat(count) {
        println("This is $it repeat")
    }
}

fun inTest(count: Int) {

    for (index in 0..count) {
        println("This is $index repeat")
    }
}

fun untilTest(count: Int) {

    for (index in 0 until count) {
        println("This is $index repeat")
    }
}

fun rangeToTest(count: Int) {

    for (index in 0.rangeTo(count)) {
        println("This is $index repeat")
    }
}

infix fun Int.myRange(to: Int): IntRange {
    if (to < Int.MIN_VALUE) return IntRange.EMPTY
    return this..to
}

fun myRangeTest(count: Int) {
    for (index in 0 myRange count) {
        println("This is $index repeat")
    }
}

fun callbackWhatSelected(data: List<String>, callback: (String, Int) -> Unit) {
    val position = System.currentTimeMillis() % data.size
    callback.invoke(data[position.toInt()], position.toInt())
}

fun showSelect(value: String, index: Int) {
    println("你选择了$index  值为:   $value")
}