package com.hsicen.state

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/******====== 12.重组作用域 和 remember ======******/
/**
 * 运行时拿到某行代码:
 *   反射/ASM -> 不算
 *   compose -> Recompose Scope
 *
 * Recompose Scope: 重组作用域
 *   指的是在 Compose 的 @Composable 代码中，被划归为⼀体的代码块，这些代码块会在变量发⽣变
 *   化从⽽导致界⾯需要重新组合（重组、Recompose）的时候，被⼀起标记为⽆效、并在稍后⼀起执⾏。
 *
 * remember 为缓存作用域，用来在 Composable 函数中声明的变量防止 Recompose 造成变量重复初始化
 * 带参数的 remember, 可以根据 key 是否改变来决定是否使用上次计算的缓存
 */
fun ComponentActivity.stateScreen120() {
  setContent { // Recompose Scope
    var name by mutableStateOf("hsicen")

    Text(
      name,
      textAlign = TextAlign.Center,
      fontSize = 24.sp,
      modifier = Modifier.padding(16.dp)
    )

    lifecycleScope.launch {
      delay(3000)
      name = "黄思程~~~"
    }
  }
}

// 缩小 重组作用域(再包一层)
fun ComponentActivity.stateScreen121() {
  setContent {
    var name by mutableStateOf("hsicen")
    Button(onClick = {
      Log.d(MainActivity.TAG, "stateScreen121: 点击.")
    }, modifier = Modifier.padding(16.dp)) { // Recompose Scope
      Text(name, textAlign = TextAlign.Center, fontSize = 24.sp)
    }

    lifecycleScope.launch {
      delay(3000)
      name = "黄思程~~~"
    }
  }
}

// 使用 remember 避免 Recompose 时代码重复执行, 直接使用缓存数据
fun ComponentActivity.stateScreen122() {
  setContent { // Recompose Scope
    var name by remember { mutableStateOf("hsicen") }
    Text(
      name, textAlign = TextAlign.Center, fontSize = 24.sp,
      modifier = Modifier.padding(16.dp)
    )

    LaunchedEffect(Unit, block = {
      delay(3000)
      name = "黄思程~~~"
    })
  }
}

/*****
 * 带参数的 remember. 只要参数不变, remember 包裹的代码就不会重复执行.
 * 重复的 str 不会计算长度
 * */
@Composable
private fun ShowContent123(str: String) {
  val len = remember(str) { str.length }
  Text(text = "content is: $len")
}