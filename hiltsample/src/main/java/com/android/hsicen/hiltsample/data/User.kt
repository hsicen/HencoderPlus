package com.android.hsicen.hiltsample.data

import javax.inject.Inject

/**
 * 作者：hsicen  2020/8/14 10:56
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class User(var id: Int, var name: String, var mood: String) {

    @Inject //向外提供实例
    constructor() : this(1, "hsicen", "毫无波澜")
}
