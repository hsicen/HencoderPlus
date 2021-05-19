package com.hsicen.livedata

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val textView = TextView(this)
    textView.text = "Hello World!"
    addContentView(textView, ViewGroup.LayoutParams(500, 200))
  }
}