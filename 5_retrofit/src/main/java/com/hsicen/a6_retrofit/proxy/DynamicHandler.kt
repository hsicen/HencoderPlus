package com.hsicen.a6_retrofit.proxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

/**
 * 作者：hsicen  2020/2/26 17:28
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：动态代理类，所有类的行为都要经过这个类来实现
 */
class DynamicHandler(private val subject: Any?) : InvocationHandler {

  override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
    println("Before method")
    println("Call method:   $method")

    val obj = method?.invoke(subject, args)
    println("After Method\n")

    return obj
  }

}