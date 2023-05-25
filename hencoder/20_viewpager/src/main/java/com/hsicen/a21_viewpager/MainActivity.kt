package com.hsicen.a21_viewpager

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onTouchEvent(event: MotionEvent?): Boolean {
    Log.d("hsc", "点击")
    return true
  }
}
