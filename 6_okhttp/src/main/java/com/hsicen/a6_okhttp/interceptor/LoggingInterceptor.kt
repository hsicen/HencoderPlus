package com.hsicen.a6_okhttp.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * <p>作者：hsicen  2019/12/12 13:59
 * <p>邮箱：codinghuang@163.com
 * <p>作用：打印每次的请求参数日志和响应的响应日志
 * <p>描述：请求拦截器
 *
 */
class LoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        //1. 前置工作
        val request = chain.request()
        val start = System.nanoTime()
        Log.i(
            "hsc",
            "\n发起请求：\nurl：${request.url}\nconnection：${chain.connection()}\nheaders：${request.headers}"
        )

        //2. 交由下一个Interceptor处理
        val response = chain.proceed(request)

        //3. 后置工作
        val end = System.nanoTime()
        Log.i(
            "hsc",
            "\n结束请求：\nurl：${response.request.url}\nheaders：${response.headers}\n请求耗时：${(end - start) / (1e6)}ms"
        )

        return response
    }


}