package com.hsicen.roomsample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlin.concurrent.thread

/**
 * 作者：hsicen  2020/10/8 13:47
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class UserViewModel(private val app: Application) : AndroidViewModel(app) {

    private val _userData =
        AppDatabase.getDatabase(app)
            .userDao()
            .loadAllUser()

    val userData: LiveData<List<User>>
        get() = _userData

    fun insertUser(user: User) {
        thread {
            AppDatabase.getDatabase(app)
                .userDao()
                .insertUser(user)
        }
    }

}