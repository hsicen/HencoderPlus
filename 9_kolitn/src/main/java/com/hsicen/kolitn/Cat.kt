package com.hsicen.kolitn

/**
 * 作者：hsicen  2020/4/2 16:18
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class Cat private constructor(name: String) {

    init {
        println("The primary constructor init, name= $name")
    }

    constructor(name: String, age: Int) : this(name) {
        println("The second constructor init,  age= $age")
    }

    constructor(name: String, age: Int, color: String) : this(name, age) {
        println("The third constructor init")
    }
}