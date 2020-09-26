package com.hsicen.coroutine

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        zipRequest()
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
}