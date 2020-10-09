package com.hsicen.roomsample

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * 作者：hsicen  2020/10/8 12:28
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：用户访问接口定义
 */

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("select * from User")
    fun loadAllUser(): LiveData<List<User>>

    @Query("select * from User where _id = :id")
    fun loadUserById(id: Int): User
}
