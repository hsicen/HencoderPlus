package com.hsicen.koinsample.data

/**
 * 作者：hsicen  2020/8/16 17:59
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：创建依赖注入对象
 */
class User(var id: Int, var name: String, var mood: String) {

  constructor() : this(1, "hsicen", "毫无波澜")
}
