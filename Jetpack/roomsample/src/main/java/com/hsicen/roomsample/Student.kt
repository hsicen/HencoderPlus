package com.hsicen.roomsample

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 作者：hsicen  2020/10/8 12:37
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var age: Int
)