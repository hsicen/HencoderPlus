package com.hsicen.kolitn.list

/**
 * 作者：hsicen  6/24/21 11:33
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：排序
 */

fun main() {
    sort2()
}

fun sort1() {
    val list1 = listOf(2, 1, 4, 3, 6, 5, 8, 7, 10, 9)
    println(list1.sorted())
    println(list1.reversed())
    println(list1)
}

fun sort2() {
    val list1 = mutableListOf(2, 1, 4, 3, 6, 5, 8, 7, 10, 9)
    println(list1.sortDescending())
    println(list1)
}
