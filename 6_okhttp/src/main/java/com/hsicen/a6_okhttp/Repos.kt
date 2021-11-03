package com.hsicen.a6_okhttp

/**
 * <p>作者：hsicen  2019/12/12 10:21
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：github仓库
 */
data class Repos(
  val id: Long = 0,
  val name: String = "",
  val url: String = "",
  val private: Boolean = false
) {
  override fun toString(): String {
    return "id：$id\nname：$name\nurl：$url\nprivate：$private\n\n\n"
  }
}
