package com.hsicen.a6_retrofit.proxy

/**
 * 作者：hsicen  2020/2/26 17:26
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：真实的代理对象
 */
class RealSubject : Subject {

    override fun hello(str: String) {
        println("Hello  $str")
    }

    override fun bye(): String {
        println("Goodbye")
        return "Over"
    }
}
