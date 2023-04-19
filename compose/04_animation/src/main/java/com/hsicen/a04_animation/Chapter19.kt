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
import kotlinx.coroutines.delay

/******====== 19. 流程定制型动画 - Animatable ======******/
/**
 * Animatable 动画 -> 对动画过程进行控制，适用于动画流程定制
 *
 * Animatable
 *  1. 可以实现动画流程细节的定制，animatableXxxAsState 只针对状态转移的动画场景
 *  2. Animatable 是 animatableXxxAsState 的底层实现
 *
 * TwoWayConverter
 *  用于把属性动画和 Compose 内部的 AnimationVector1D/2D/3D/4D 做转化计算用；
 *  常用的类型(如 Float, Dp, Size 等) 可以直接使用系统自带的 Dp.VectorConverter, Float.VectorConverter 等
 *  特殊类型的属性需要自定义 TwoWayConverter(VectorConverters.kt)
 *
 * LaunchedEffect
 *  用于启动协程
 *  和普通启动协程的方法(如 lifecycleScope.launch )的区别：
 *   1. 和 Compose 做了结合，不会在 Recompose 过程中发生意外重启
 *   2. 可以填写一个或多个 keyN 参数，用于在需要的时候主动重启协程
 */
fun ComponentActivity.composeAnimation03() {
  setContent {
    // 1. 创建动画对象 Animatable(float/color)
    val animSize = remember { Animatable(48.dp, Dp.VectorConverter) }

    // 2. 使用动画值
    Box(
      contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
      Box(
        modifier = Modifier
          .size(animSize.value)
          .background(Color.Green)
      )

      // 3. 动画渐变过程配置  animateTo()
      // Recompose 优化  remember(key1)，提高重组效率
      LaunchedEffect(key1 = Unit, block = {
        delay(1000)
        animSize.animateTo(200.dp)
      })
    }
  }
}

fun ComponentActivity.composeAnimation04() {
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

      // Recompose 优化  remember(key1)
      LaunchedEffect(key1 = big, block = {
        animSize.animateTo(if (big) 200.dp else 48.dp)
      })
    }
  }
}

// 带回弹效果的动画
fun ComponentActivity.composeAnimation05() {
  var big by mutableStateOf(false)
  setContent {
    val animSize = remember { Animatable(16.dp, Dp.VectorConverter) }

    Box(
      contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
      Box(modifier = Modifier
        .size(animSize.value)
        .background(Color.Green)
        .clickable {
          big = !big
        })

      // Recompose 优化  remember(key1)
      LaunchedEffect(key1 = big, block = {
        animSize.snapTo(if (big) 300.dp else 0.dp) // 动画起始值
        animSize.animateTo(if (big) 200.dp else 100.dp, spring(Spring.DampingRatioMediumBouncy)) // 动画平缓过渡
      })
    }
  }
}