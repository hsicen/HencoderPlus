package com.hsicen.roomsample

import androidx.room.*

/**
 * 作者：hsicen  2020/10/8 12:40
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

@Dao
interface StudentDao {

    @Insert
    fun insertStudent(student: Student): Long

    @Delete
    fun deleteStudent(student: Student)

    @Update
    fun updateStudent(student: Student)

    @Query("select * from Student")
    fun loadAllStudent(): List<Student>

    @Query("select * from Student where id = :id")
    fun loadStudentById(id: Int): Student
}