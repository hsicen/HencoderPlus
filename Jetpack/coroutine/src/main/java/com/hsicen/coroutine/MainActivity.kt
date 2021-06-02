package com.hsicen.coroutine

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.hsicen.coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private val mainScope by lazy { MainScope() }
  private val flowModel by lazy {
    ViewModelProvider(this).get(FlowViewModel::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    lifecycleScope.launchWhenStarted {
      flowModel.uiState.collect {
        when (it) {
          is LoadState.Initial -> binding.tvInfo.text = "Initial"
          is LoadState.Loaded -> binding.tvInfo.text = it.news.toString()
          is LoadState.Error -> binding.tvInfo.text = it.exception.message
        }
      }
    }

    binding.button.setOnClickListener {
      flowModel.fetchNews()
    }
  }

  private fun test() {
    zipRequest()

    lifecycleScope.launch {
      delay(1000)
      println("Hello World")
    }

    lifecycleScope.launchWhenResumed {
      kotlinx.coroutines.delay(1000)
      println("Hello World")
    }
  }

  /*** 利用协程合并请求*/
  @SuppressLint("SetTextI18n")
  private fun zipRequest() {
    GlobalScope.launch(Dispatchers.Main) {
      val result1 = async { requestData1() }
      val result2 = async { requestData2() }

      binding.tvInfo.text = "${result1.await()} + ${result2.await()}"
    }
  }

  private suspend fun requestData1() = withContext(Dispatchers.IO) {
    delay(3000L)
    "hsicen"
  }

  private suspend fun requestData2() = withContext(Dispatchers.IO) {
    delay(1500)
    "miky"
  }

  override fun onDestroy() {
    super.onDestroy()
    mainScope.cancel()
  }
}