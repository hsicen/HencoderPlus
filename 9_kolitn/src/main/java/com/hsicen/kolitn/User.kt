package com.hsicen.kolitn

/**
 * <p>作者：Hsicen  2019/9/21 18:42
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：用户
 */
class User() {

    constructor(name: String) : this() {
        println("构造函数 $name")
    }

    init {
        println("init 代码块")
    }

    @JvmOverloads
    fun personInfo(name: String, age: Int = 0) {
        println(" $name   $age")
    }

}