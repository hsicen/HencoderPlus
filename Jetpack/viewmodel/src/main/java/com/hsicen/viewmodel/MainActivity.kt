package com.hsicen.viewmodel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.viewmodel.databinding.ActivityMainBinding

/**
 * 作者：hsicen  5/18/21 17:44
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：ViewModel
 *
 * ViewModel与AndroidViewModel的区别是，AndroidViewModel有Application参数，用于需要Context的场景，没有其他区别
 */
class MainActivity : AppCompatActivity() {
  private lateinit var mainBinding: ActivityMainBinding
  private val countModel by viewModels<CountViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mainBinding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(mainBinding.root)

    countModel.count.observe(this) {
      mainBinding.content.text = "$it"
    }
    countModel.startTiming()

    mainBinding.btnViewModel.setOnClickListener {
      startActivity(Intent(this, SecondActivity::class.java))
    }
  }

  override fun onDestroy() {
    Log.d("hsc", "${this.javaClass.simpleName} onDestroy()")
    super.onDestroy()
  }
}