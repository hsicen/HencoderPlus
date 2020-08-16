package com.hsicen.a36_launch_mode.singleTask

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a36_launch_mode.R

class SingleTask2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_task2)

        val mService = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        println("当前任务栈：" + mService.appTasks)
    }
}
