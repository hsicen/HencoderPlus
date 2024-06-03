package com.hsicen.a01_introduce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/******====== 01.Coroutines introduce ======******/
class MainActivity : ComponentActivity() {
  private val mViewModel by viewModels<BasicViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      basics10()
    }
  }

  private fun useInAndroid() {
    // lifecycleScope Dispatcher 为 Main
    lifecycleScope.launch {
      println("@@@lifecycleScope.launch1: ${Thread.currentThread().name}")
      callSuspend()
      println("@@@lifecycleScope.launch2: ${Thread.currentThread().name}")
    }

    mViewModel.showThread()
  }

  private suspend fun callSuspend() {
    withContext(Dispatchers.Default) {
      delay(500)
      println("@@@lifecycleScope.suspend: ${Thread.currentThread().name}")
    }
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    fontSize = 25.sp,
    modifier = modifier
      .padding(16.dp)
      .background(Color.Yellow)
      .padding(16.dp)
      .clickable {
        println("@@@ click from mouse.")
      }
  )
}
