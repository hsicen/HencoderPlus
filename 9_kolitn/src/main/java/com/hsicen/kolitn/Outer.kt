package com.hsicen.kolitn

/**
 * 作者：hsicen  2020/4/3 11:18
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class Outer {
    private val bar: Int = 1

    inner class Nested {
        fun foo() = bar
    }
}

fun main() {

    val demo = Outer().Nested().foo()
}