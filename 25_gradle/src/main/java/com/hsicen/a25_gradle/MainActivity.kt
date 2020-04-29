package com.hsicen.a25_gradle

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Looper
import android.os.MessageQueue
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_launch.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        view_change.setOnClickListener {
            Thread.sleep(500)
            val red = Random.nextInt(0, 255)
            val green = Random.nextInt(0, 255)
            val blue = Random.nextInt(0, 255)
            val alpha = Random.nextInt(0, 255)

            view_change.setBackgroundColor(Color.argb(alpha, red, green, blue))
        }
    }

    override fun onResume() {
        super.onResume()

        //在空闲时做操作的任务
        val starMsg = MessageQueue.IdleHandler {
            Thread.sleep(2000)
            println("hsc  当前线程：${Thread.currentThread().name}")
            tv_launch.text = "空闲时间：${++count}"
            true
        }

        //将任务添加到MessageQueue中
        Looper.myQueue().addIdleHandler(starMsg)
    }
}
