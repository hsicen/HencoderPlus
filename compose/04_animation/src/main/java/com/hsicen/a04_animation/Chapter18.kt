package com.hsicen.a04_animation

import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


/******====== 18. 状态转移型动画 - animateXXXAsState ======******/
/**
 * animateXXXAsState 动画 -> 对动画的目标值进行控制，适用于状态切换
 *
 * 1. animateXXXAsState 返回的是一个 State 对象，而不是 MutableState，不能够改变；能够监听值的变化，触发 Recompose
 * 2. animateXXXAsState 动画渐变目标值参数是可改变的，通过改变这个值来实现动画效果
 * 3. 没法手动设置动画的初始值，默认当前值为初始值
 */
fun ComponentActivity.composeAnimation01() {

  setContent {
    var size by remember { mutableStateOf(48.dp) }

    Box(
      contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
      Box(modifier = Modifier
        .size(size)
        .background(Color.Green)
        .clickable {
          // 触发 Recompose
          if (SystemClock.uptimeMillis() % 3 != 0L) {
            size += 20.dp
          } else {
            size -= 20.dp
          }
        })
    }
  }
}

fun ComponentActivity.composeAnimation02() {
  var size by mutableStateOf(100.dp)

  setContent {
    // val animSize by  remember { animateDpAsState(48.dp) } // State not mutableState
    val animSize by animateDpAsState(size) // State, just getValue

    Box(
      contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
      Box(modifier = Modifier
        .size(animSize)
        .background(Color.Green)
        .clickable {
          if (SystemClock.uptimeMillis() % 3 != 0L) {
            size += 20.dp
          } else {
            size -= 20.dp
          }
        })
    }
  }
}