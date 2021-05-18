package com.hsicen.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.viewmodel.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
  private lateinit var secondBinding: ActivitySecondBinding
  private val viewModel by viewModels<CountAndroidModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    secondBinding = ActivitySecondBinding.inflate(layoutInflater)
    setContentView(secondBinding.root)

    viewModel.count.observe(this) {
      secondBinding.content.text = "$it"
    }

    viewModel.startTiming()

    secondBinding.btnFinish.setOnClickListener {
      finish()
    }
  }

  override fun onDestroy() {
    Log.d("hsc", "${this.javaClass.simpleName} onDestroy()")
    super.onDestroy()
  }
}