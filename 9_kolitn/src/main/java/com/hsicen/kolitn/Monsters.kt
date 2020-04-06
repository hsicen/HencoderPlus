package com.hsicen.kolitn

import java.io.FileInputStream
import java.io.InputStream

/**
 * <p>作者：Hsicen  2019/9/23 9:47
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：where 上界中只能有一个为具体类，其它必须为接口类
 */
class Monsters<T> where T : Animal, T : Person {


    fun eat(food: String) {

        val fs = FileInputStream("")
        fs.use {

        }


        fun cook() {
            println("The $food is going to be cooked")
        }

        //会创建一个函数对象， 有一定的性能损耗
        cook()
    }

}