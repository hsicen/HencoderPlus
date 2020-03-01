package com.hsicen.a32_view_draw_process

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        childText.setOnClickListener {
            it.requestLayout()
            thread {
                childText.text = "线程： ${Thread.currentThread().name}"
            }
        }
    }
}
