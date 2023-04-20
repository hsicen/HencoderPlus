package com.hsicen.a04_animation

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.keyframes
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

/******====== 22. AnimationSpec - KeyframesSpec ======******/
/**
 *  ⽤关键帧来把动画分成多段的 AnimationSpec，实质上可以看作是「多段式」的 TweenSpec
 *   - 设置关键帧： <元素值> at <时间点>
 *   - 设置从关键帧到下⼀个关键帧（或结束点）的动画曲线： <关键帧设置> with <Easing>
 */
fun ComponentActivity.composeAnimation09() {
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

      LaunchedEffect(key1 = big, block = {
        animSize.animateTo(if (big) 200.dp else 48.dp, keyframes {
          durationMillis = 450 // 动画时长
          delayMillis = 500 // 动画延时

          (if (big) 48.dp else 200.dp) at 0 with LinearEasing
          144.dp at 150
          20.dp at 300 with FastOutSlowInEasing
        })
      })
    }
  }
}