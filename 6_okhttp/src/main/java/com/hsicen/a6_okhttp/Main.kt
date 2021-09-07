package com.hsicen.a6_okhttp

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket

/**
 * 作者：hsicen  2020/9/1 8:37
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：手动创建TCP链接
 */

fun main() {
    try {
        val socket = Socket("rengwuxian.com", 80)
        val writer = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
        val reader = BufferedReader(InputStreamReader(socket.getInputStream()))

        //请求报文
        writer.write("GET / HTTP/1.1\nHost:rengwuxian.com\n\n")
        writer.flush()

        var msg: String
        while (reader.readLine().also { msg = it } != null) {
            println(msg)
        }
    } catch (e: Exception) {
        e.printStackTrace(System.err)
    }
}