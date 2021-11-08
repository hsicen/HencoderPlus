package com.hsicen.a25_gradle

import android.content.Intent
import android.graphics.Color
import android.os.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a25_gradle.databinding.ActivityMainBinding
import kotlin.concurrent.thread
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private var count = 0
  private lateinit var mThreadHandler: Handler

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.tvLaunch.setOnClickListener {
      startActivity(Intent(this, SecondActivity::class.java))
    }

    binding.viewChange.setOnClickListener {
      println(
        "View的起始位置： [left:${binding.viewChange.left}, " +
          "top:${binding.viewChange.top}, " +
          "right:${binding.viewChange.right}, " +
          "bottom:${binding.viewChange.bottom}]"
      )
      val red = Random.nextInt(0, 255)
      val green = Random.nextInt(0, 255)
      val blue = Random.nextInt(0, 255)
      val alpha = Random.nextInt(0, 255)
      binding.viewChange.setBackgroundColor(Color.argb(alpha, red, green, blue))

      binding.viewChange.animate()
        .translationX(200f)
        .setDuration(500)
        .start()
      println(
        "View的结束位置： [left:${binding.viewChange.left}, " +
          "top:${binding.viewChange.top}, " +
          "right:${binding.viewChange.right}, " +
          "bottom:${binding.viewChange.bottom}]"
      )

      val msg = Message.obtain()
      msg.obj = "red>$red  green>$green  blue>$blue  线程：" + Thread.currentThread()
      msg.what = 99
      msg.arg1 = 88
      msg.arg2 = 66
      mThreadHandler.sendMessage(msg)
    }

    testThread()
  }

  override fun onResume() {
    super.onResume()

    //在空闲时做操作的任务
    val starMsg = MessageQueue.IdleHandler {
      println("hsc  当前线程：${Thread.currentThread().name}")
      binding.tvLaunch.text = "空闲时间：${++count}"
      true
    }

    //将任务添加到MessageQueue中
    Looper.myQueue().addIdleHandler(starMsg)
  }

  private fun testThread() {

    thread {
      Looper.prepare()
      mThreadHandler = Handler(Looper.myLooper() ?: return@thread) {
        println(it)
        Toast.makeText(this, "干嘛给我发消息 ${Thread.currentThread().name}", Toast.LENGTH_SHORT)
          .show()
        true
      }
      Looper.loop()
    }
  }
}
