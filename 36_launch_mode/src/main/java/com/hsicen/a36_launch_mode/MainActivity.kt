package com.hsicen.a36_launch_mode

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.hsicen.a36_launch_mode.singleTask.SingleTask1Activity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 作者：hsicen  2020/5/26 9:11
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：启动模式实践
 *
 * 多个Activity以SingleInstance模式启动，即使任务栈名字相同，也会一个Activity创建一个任务栈
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mService = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        println("当前任务栈：" + mService.appTasks)

        btn_jump.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        btn_jump1.setOnClickListener {
            startActivity(Intent(this, SingleTask1Activity::class.java))
        }
    }

    /*** 获取图片类型*/
    private fun getBitmapType(bitmapPath: String) {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(bitmapPath, options)
        options.inJustDecodeBounds = false

        val mimeType = options.outMimeType
        Log.d("图片类型", mimeType)
    }
}
