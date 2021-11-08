package com.hsicen.a6_retrofit.proxy

import java.lang.reflect.Proxy

/**
 * 作者：hsicen  2020/2/26 17:36
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：代理类
 */
object Client {

  @JvmStatic
  fun main(args: Array<String>) {
    val realSubject = RealSubject()
    val mHandler = DynamicHandler(realSubject)

    val subject = Proxy.newProxyInstance(
      mHandler.javaClass.classLoader,
      realSubject.javaClass.interfaces,
      mHandler
    ) as? Subject

    println(subject?.javaClass?.name)
    subject?.hello("World")
    val result = subject?.bye()

    println("The result is $result")
  }
}
