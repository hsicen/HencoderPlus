package com.hsicen.kolitn

/**
 * 作者：hsicen  2020/4/1 10:55
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
data class NewPerson(var name: String, var age: Int) {

    infix fun addAge(inc: Int) {
        age += inc
    }

}