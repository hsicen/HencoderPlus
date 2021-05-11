package com.hsicen.touch

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import com.hsicen.touch.databinding.ActivityMainBinding
import com.hsicen.touch.sample.TouchEventActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mTouchDelegate by lazy {
        GestureDetectorCompat(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent?) = true

            override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                Toast.makeText(this@MainActivity, "单击操作", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@MainActivity, TouchEventActivity::class.java))
                return super.onSingleTapConfirmed(e)
            }

            override fun onDoubleTap(e: MotionEvent?): Boolean {
                Toast.makeText(this@MainActivity, "双击操作", Toast.LENGTH_SHORT).show()
                return super.onDoubleTap(e)
            }
        })
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.touchView.setOnClickListener {
            Toast.makeText(this, "被点击了", Toast.LENGTH_SHORT).show()
        }

        binding.ivTouchSample.setOnTouchListener { _, event ->
            mTouchDelegate.onTouchEvent(event)
        }
    }
}
