package com.hsicen.livedata

/**
 * 作者：hsicen  5/22/21 16:56
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
data class User(val name: String, val gender: String, val age: Int) {

  init {
    val items = listOf(1, 2, 3, 4, 5, 6)
    items.forEach {
      println(it)
    }

    println("ab" in "abc".."xyz")
  }

}
