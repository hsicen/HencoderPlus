package com.hsicen.a22_thread

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a22_thread.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private val mHandler = Handler {
        //handle message
        val whatStr = (it.obj as? String) ?: ""
        mBinding.tvContent.text = whatStr

        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnSend.setOnClickListener {
            thread {
                val message = Message()
                message.obj = "The message from ${Thread.currentThread().name}"
                mHandler.sendMessage(message)
            }
        }
    }
}
