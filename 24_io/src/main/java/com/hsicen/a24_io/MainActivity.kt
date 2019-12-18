package com.hsicen.a24_io

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_get.setOnClickListener { getStackTrace() }
    }

    private fun getStackTrace() {
        val allStackTraces = Thread.getAllStackTraces()
        val size = allStackTraces.size
    }
}
