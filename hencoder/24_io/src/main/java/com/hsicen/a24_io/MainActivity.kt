package com.hsicen.a24_io

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a24_io.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnGet.setOnClickListener { getStackTrace() }
    }

    private fun getStackTrace() {
        val allStackTraces = Thread.getAllStackTraces()
        val size = allStackTraces.size
    }
}
