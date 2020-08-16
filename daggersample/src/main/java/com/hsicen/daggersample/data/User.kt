package com.hsicen.daggersample.data

import com.hsicen.daggersample.dagger.scope.ActivityScope
import javax.inject.Inject

/**
 * 作者：hsicen  2020/8/16 16:14
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

@ActivityScope
class User(var id: Int, var name: String, var mood: String) {

    @Inject
    constructor() : this(1, "hsicen", "毫无波澜")
}