package com.android.hsicen.hiltsample.data

import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * 作者：hsicen  2020/8/14 10:56
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

@ActivityScoped //Activity作用域  同一个Activity中只存在一个实例
class User(var id: Int, var name: String, var mood: String) {

    @Inject //向外提供实例
    constructor() : this(1, "hsicen", "毫无波澜")
}
