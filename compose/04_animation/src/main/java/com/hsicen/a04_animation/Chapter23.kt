package com.hsicen.a04_animation

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
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

/******====== 23. AnimationSpec - SpringSpec ======******/
/**
 * 基于弹簧的物理模型的 AnimationSpec，动画的时⻓由弹簧模型⾃⼰计算出，不能⼿动设置。
 *
 * SpringSpec
 *  dampingRatio: 弹簧的阻尼⽐，即弹簧「有多 Q 弹」
 *  stiffness: 弹簧的刚度，即弹簧「有多硬 / 多想弹回去」
 *  visibilityThreshold: 直接判断弹簧可以停⽌的阈值
 *
 * initialVelocity：初始速度
 */
fun ComponentActivity.composeAnimation10() {
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
        // animSize.animateTo(if (big) 200.dp else 48.dp, spring(0.1f))
        // animSize.animateTo(if (big) 200.dp else 48.dp, spring(1f))
        // animSize.animateTo(if (big) 200.dp else 48.dp, spring(0.1f, Spring.StiffnessHigh))
        // animSize.animateTo(if (big) 200.dp else 48.dp, spring(0.1f, Spring.StiffnessVeryLow))
        // animSize.animateTo(if (big) 200.dp else 48.dp, spring(0.1f, Spring.StiffnessVeryLow, 5.dp))
        animSize.animateTo(100.dp, spring(0.01f, Spring.StiffnessMedium), 2000.dp)
      })
    }
  }
}