package com.hsicen.a6_okhttp

import java.net.InetAddress

/**
 * 作者：hsicen  2020/9/2 9:25
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

fun main(args: Array<String>) {
  println("Resolve: " + InetAddress.getAllByName("www.hencoder.com").toList())
  println("Resolve: " + InetAddress.getAllByName("www.baidu.com").toList())
}