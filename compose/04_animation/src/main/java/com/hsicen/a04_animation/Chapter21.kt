package com.hsicen.a04_animation

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.SnapSpec
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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

/******====== 21. AnimationSpec - SnapSpec ======******/
/**
 * 直接到达⽬标值，没有动画过程。但可以⽤ delay 参数来设置起始延迟。
 *
 * SnapSpec/snap - 可以设置延时
 * snapTo
 */
fun ComponentActivity.composeAnimation08() {
  var big by mutableStateOf(false)
  setContent {
    val animSize = remember { Animatable(48.dp, Dp.VectorConverter) }

    Box(
      contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
      Box(modifier = Modifier
        .size(animSize.value)
        .background(Color.Green)
        .clickable {
          big = !big
        })

      // SnapSpec 闪变
      LaunchedEffect(key1 = big, block = {
        animSize.animateTo(if (big) 200.dp else 100.dp, SnapSpec(500))
      })
    }
  }
}