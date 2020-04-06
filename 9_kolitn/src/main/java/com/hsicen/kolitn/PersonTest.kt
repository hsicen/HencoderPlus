package com.hsicen.kolitn

/**
 * 作者：hsicen  2020/4/1 10:56
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

fun main() {
    val person = NewPerson("hsicen", 18)
    val person2 = NewPerson("hsicen", 18)
    person addAge 2

    println(person)
    println(person2)

    person.changeName("vince")
    println(person)
    println(person2)


}

fun NewPerson.changeName(newName: String) {
    this.name = newName
}