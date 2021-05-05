package com.hsicen.a5_kotlin.base

/**
 * 作者：hsicen  1/27/21 9:13 PM
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：国家
 */
class Country(val name: String, val population: Long) {

    override fun toString(): String {
        return "$name -> $population"
    }
}
