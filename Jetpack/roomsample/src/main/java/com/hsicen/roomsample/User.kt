package com.hsicen.roomsample

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 作者：hsicen  2020/10/8 12:09
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：Student
 */

@Entity
class User(
    @PrimaryKey(autoGenerate = true)
    val _id: Int? = null,
    var name: String,
    var age: Int,
    var gender: String
) {

    constructor(name: String, age: Int, gender: String) : this(null, name, age, gender)

    override fun toString(): String {
        return "[id=$_id, name=$name, age=$age, gender=$gender] \n"
    }
}
