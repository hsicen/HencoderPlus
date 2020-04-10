package com.hsicen.touch

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mTouchDelegate by lazy {
        GestureDetectorCompat(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent?) = true

            override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                Toast.makeText(this@MainActivity, "单击操作", Toast.LENGTH_SHORT).show()
                return super.onSingleTapConfirmed(e)
            }

            override fun onDoubleTap(e: MotionEvent?): Boolean {
                Toast.makeText(this@MainActivity, "双击操作", Toast.LENGTH_SHORT).show()
                return super.onDoubleTap(e)
            }
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        touchView.setOnClickListener {
            Toast.makeText(this, "被点击了", Toast.LENGTH_SHORT).show()
        }

        iv_touch_sample.setOnTouchListener { _, event ->
            mTouchDelegate.onTouchEvent(event)
        }
    }
}
