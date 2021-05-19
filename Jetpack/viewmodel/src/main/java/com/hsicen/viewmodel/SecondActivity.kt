package com.hsicen.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.viewmodel.databinding.ActivitySecondBinding

/**
 * 作者：hsicen  5/18/21 21:15
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：AndroidViewModel
 */
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