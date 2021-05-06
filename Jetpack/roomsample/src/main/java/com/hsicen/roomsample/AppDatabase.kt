package com.hsicen.roomsample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * 作者：hsicen  2020/10/8 12:36
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：数据库(关联表)
 */

@Database(entities = [Student::class, User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun studentDao(): StudentDao

    companion object {
        private const val DATABASE_NAME = "hsicen.db"

        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let { return it }

            return Room
                .databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )/*.allowMainThreadQueries() //设置允许主线程操作Room*/
                .build().also { instance = it }
        }

    }
}