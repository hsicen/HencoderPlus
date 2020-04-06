package com.hsicen.kolitn

import android.view.View
import kotlin.random.Random

/**
 * 作者：hsicen  2020/4/2 10:17
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：循环遍历测试
 */
fun main() {

    //indicateLoop()
    //changeTwo()

    //takeIfTest()
    //genericTest()

    //constructorTest()
    /*val values = Food.values()
    val food = values[0]
    println(food.name)
    println(Food.valueOf("BANANA"))*/

    //alsoTest()

    maxTest()
}

fun maxTest() {
    val numbers = listOf(99,5, 10, 4, 3, 9, 66, 39, 42)
    val min3Remainder = numbers.minBy { it % 3 }
    println(min3Remainder)

    val strings = listOf("one", "two", "three", "four")
    val longestString = strings.maxWith(compareBy { it.length })
    println(longestString)
}

fun alsoTest() {
    val listOf = listOf(1, 2, 3, 4, 5, 6)
    val plusList = listOf + 7 + 8 + 9
    val minusList = listOf - 2 - 4 - 6

    println(listOf)
    println(plusList)
    println(minusList)
}

fun constructorTest() {
    val cat = Cat("Tom", 5, "白色")
    print(cat)
}

fun genericTest() {
    val intNum1 = Array(5) { it * it }
    for (item in intNum1) {
        println("$item")
    }

    val intNum2 = arrayOf(1, 2, 3)
    for (item in intNum2) {
        println("$item")
    }

    val intNum3 = IntArray(3) { it }
    for (item in intNum3) {
        println("$item")
    }

    val intNum4 = intArrayOf(1, 2, 3)

    val intNum5 = listOf(1, 2, 3)

    val intNum6 = mutableListOf(1, 2, 3)

    val mList: List<out View> = ArrayList()
    //val any = mList[1]


    for ((index, value) in intNum6.withIndex()) {

        for (item in intNum6) {
            if (index == 2) return
        }
        println("The index is $index  with the value $value")
    }

}

fun changeTwo() {
    var a = 34
    var b = 12

    println("Before  a=$a   b=$b")
    a = b.also { b = a }
    println("After  a=$a   b=$b")
}

fun takeIfTest() {
    val randomNum = Random(100).nextInt()
    val qualifiedNum = randomNum.takeIf { it > 32 } //int or null

    println("The num is qualified $qualifiedNum")
}

fun indicateLoop() {
    val intArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)

    for (index in intArray.indices) {
        println("The index is $index  and the value is ${intArray[index]}")
    }
}