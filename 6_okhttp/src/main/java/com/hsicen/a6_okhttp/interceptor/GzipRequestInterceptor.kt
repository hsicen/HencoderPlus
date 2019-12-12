package com.hsicen.a6_okhttp.interceptor

import okhttp3.Interceptor
import okhttp3.RequestBody
import okhttp3.Response
import okio.BufferedSink
import okio.GzipSink
import okio.buffer

/**
 * <p>作者：hsicen  2019/12/12 15:26
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：请求参数压缩
 */
class GzipRequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        if (originalRequest.body == null || originalRequest.header("Content-Encoding") != null) {
            return chain.proceed(originalRequest)
        }

        val compressedRequest = originalRequest.newBuilder()
            .header("Content_Encoding", "gzip")
            .method(originalRequest.method, gzip(originalRequest.body))
            .build()

        return chain.proceed(compressedRequest)
    }

    private fun gzip(body: RequestBody?): RequestBody {

        return object : RequestBody() {
            override fun contentType() = body?.contentType()

            override fun contentLength() = -1L

            override fun writeTo(sink: BufferedSink) {
                val gZipSink = GzipSink(sink).buffer()
                body?.writeTo(gZipSink)

                gZipSink.close()
            }
        }
    }

}