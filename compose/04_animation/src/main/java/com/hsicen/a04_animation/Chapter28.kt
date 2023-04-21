package com.hsicen.a04_animation

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max

/******====== 28. block 参数：监听每⼀帧 ======******/
/**
 * 作用: 监听 animateTo() 和 animateDecay() 的每⼀帧。
 * 用法: animateTo() 和 animateDecay() 的最后⼀个参数 block.
 */
fun ComponentActivity.composeAnimation15() {
  var big by mutableStateOf(false)

  setContent {
    val anim = remember { Animatable(0.dp, Dp.VectorConverter) }

    Box(
      contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxSize()
    ) {
      Box(
        modifier = Modifier
          .padding(0.dp, max(anim.value, 0.dp), 0.dp, 0.dp)
          .size(100.dp)
          .background(Color.Green)
          .clickable {
            if (anim.isRunning) return@clickable
            big = !big
          }
      )

      val spec1 = remember { exponentialDecay<Dp>(3f, 3f) }

      LaunchedEffect(big, block = {
        // anim.animateTo()  // block 参数
        anim.animateDecay((if (big) (-2500).dp else 2500.dp), spec1) {
          // 动画每一帧刷新都会回调
          Log.d("hsc", "composeAnimation15: ${anim.value}")
        }
      })
    }
  }
}