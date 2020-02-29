package com.hsicen.a6_retrofit

import com.hsicen.a6_retrofit.proxy.DynamicHandler
import com.hsicen.a6_retrofit.proxy.RealSubject
import com.hsicen.a6_retrofit.proxy.Subject
import org.junit.Test
import java.lang.reflect.Proxy

/**
 * 作者：hsicen  2020/2/26 17:54
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class DynamicTest {

    @Test
    fun testDynamic() {
        val realSubject = RealSubject()
        val mHandler = DynamicHandler(realSubject)

        val subject = Proxy.newProxyInstance(
            mHandler.javaClass.classLoader,
            realSubject.javaClass.interfaces,
            mHandler
        ) as? Subject

        subject?.hello("World")
        val result = subject?.bye()

        println("The result is $result")
    }
}