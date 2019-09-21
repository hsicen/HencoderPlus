package com.aicoder.hencoderplus

/**
 * <p>作者：Hsicen  6/13/2019 9:09 AM
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
class Account {

    @get: JvmName("banlance")
    var banlance = 100.0

    /*** 成员函数*/
    infix fun add(increase: Double) {
        this.banlance = banlance + increase
    }
}