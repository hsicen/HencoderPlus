package com.hsicen.a32_view_draw_process

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a32_view_draw_process.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.childText.setOnClickListener {
            it.requestLayout()
            thread {
                mBinding.childText.text = "线程： ${Thread.currentThread().name}"
            }
        }

        mBinding.childText.post {}

        startActivity(Intent(this, MainActivity::class.java))
    }
}
