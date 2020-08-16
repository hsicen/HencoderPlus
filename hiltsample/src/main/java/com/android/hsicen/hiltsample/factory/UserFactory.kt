package com.android.hsicen.hiltsample.factory

import com.android.hsicen.hiltsample.data.User

/**
 * 作者：hsicen  2020/8/16 21:49
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class UserFactory {

    fun newUser(): User {
        val user = User()
        user.name = "hsicen"
        user.mood = "Happy"

        return user
    }
}