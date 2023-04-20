package com.hsicen.a04_animation

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
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

/******====== 24. AnimationSpec - RepeatableSpec ======******/
/**
 * ⽤于设置 DurationBasedAnimationSpec 的重复，只能设置有限个数。
 *
 * RepeatableSpec
 *  iterations: 动画重复次数
 *  animation: 要重复的动画
 *  repeatMode: 重复的方式
 *  initialStartOffset: 初始时间偏移
 */
fun ComponentActivity.composeAnimation11() {
  var big by mutableStateOf(false)

  setContent {
    val animSize = remember { Animatable(48.dp, Dp.VectorConverter) }

    Box(
      contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
      Box(
        modifier = Modifier
          .size(animSize.value)
          .background(Color.Green)
          .clickable {
            big = !big
          })

      LaunchedEffect(key1 = big, block = {
        // animSize.animateTo(if (big) 200.dp else 48.dp, repeatable(3, tween()))
        // animSize.animateTo(if (big) 200.dp else 48.dp, repeatable(3, tween(), RepeatMode.Reverse))
        // animSize.animateTo(if (big) 200.dp else 48.dp, repeatable(3, tween(), RepeatMode.Reverse, StartOffset(500, StartOffsetType.Delay)))
        animSize.animateTo(
          if (big) 200.dp else 48.dp,
          repeatable(
            999,
            tween(),
            RepeatMode.Reverse,
            StartOffset(300, StartOffsetType.FastForward)
          )
        )
      })
    }
  }
}