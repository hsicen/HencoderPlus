package com.hsicen.a36_launch_mode

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val mService = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        println("当前任务栈：" + mService.appTasks)
    }
}
