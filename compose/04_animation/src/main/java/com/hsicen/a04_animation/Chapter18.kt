package com.hsicen.a04_animation

import android.os.SystemClock
import android.util.Log
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

// 直接改变 size，没有动画效果
fun ComponentActivity.composeAnimation01() {

  setContent {
    var size by remember { mutableStateOf(48.dp) }
    Log.d("hsc", "composeAnimation01: setContentScope")

    Box(
      contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
      Log.d("hsc", "composeAnimation01: BoxScope")
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

/**
 * 写法：
 *    1. val animSize by animateDpAsState( if(big) 96.dp else 48.dp)
 *    2. val animSize by animateDpAsState(size)
 *    关键点在于，参数⾥的数值是可变的，⽽在数值改变之后，
 *    animateXxxAsSize() 左边的值不会⽴即改变，⽽是会渐变，从⽽实现动画效果。
 *
 * 重点：
 *  1. animateXxxAsState 包含了自动刷新(Recompose)功能和对 remember { } 的嵌套；
 *     不需要再额外写 mutableStateOf 和 remember { }。
 *  2. animateXxxAsState() 返回的是 State 对象⽽不是 MutableState，所以 by 的左边应该写 val ⽽不是 var；
 *     如果想修改属性值，直接修改传入的参数，而不是去修改 animateXxxAsState() 返回的 State 对象。
 *  3. animateXxxAsState() 内部包含了 remember {} 的调⽤，所以它是⼀个 Composable 函数，
 *     所以不能在 Composable 环境的外部使⽤。
 */
fun ComponentActivity.composeAnimation02() {
  var size by mutableStateOf(100.dp)

  setContent {
    // val animSize by  remember { animateDpAsState(48.dp) } // State not mutableState
    val animSize by animateDpAsState(size) // State, just getValue
    Log.d("hsc", "composeAnimation02: ContentScope")

    Box(
      contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
      Log.d("hsc", "composeAnimation02: BoxScope")
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