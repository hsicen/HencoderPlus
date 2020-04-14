package com.hsicen.a22_thread

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val mHandler = Handler {
        //handle message
        val whatStr = (it.obj as? String) ?: ""
        tv_content.text = whatStr

        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_send.setOnClickListener {
            thread {
                val message = Message()
                message.obj = "The message from ${Thread.currentThread().name}"
                mHandler.sendMessage(message)
            }
        }
    }
}
