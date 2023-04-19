package com.hsicen.a04_animation

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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

/**
 * AnimationSpec 继承关系
 *  1.FiniteAnimationSpec
 *    (1).DurationBasedAnimationSpec
 *      ①.TweenSpec
 *      ②.SnapSpec
 *      ③.KeyframesSpec
 *    (2).RepeatableSpec
 *    (3).SpringSpec
 *  2.FloatAnimationSpec
 *    (1).FloatSpringSpec
 *    (2).FloatTweenSpec
 *  3.InfiniteAnimationSpec
 */

/******====== DurationBasedAnimationSpec ======******/
/******====== 20. AnimationSpec - TweenSpec ======******/
/**
 * TweenSpec
 *  1. 动画默认时长为 300ms，可以自定义设置
 *  2. 可以设置动画起始延时
 *  3. 用 easing: Easing 参数来设置动画的速度曲线
 *    LinearEasing  - 匀速动画
 *    FastOutSlowInEasing - 元素发生变化， A 状态到 B 状态的动画
 *    LinearOutSlowInEasing - 入场动画
 *    FastOutLinearInEasing - 出场动画
 *
 *    Easing 的实现类为 CubicBezierEasing：由 3 阶贝塞尔曲线决定速度曲线
 *
 * Tween 起源：
 *  Inbetween 补帧
 *  TweenAnimation
 *  TweenSpec 开始值和结束值已确定，补中间过程
 *  tween
 */
fun ComponentActivity.composeAnimation06() {
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
        animSize.animateTo(if (big) 200.dp else 100.dp, spring(Spring.DampingRatioHighBouncy))
      })
    }
  }
}

// TweenSpec
fun ComponentActivity.composeAnimation07() {
  var big by mutableStateOf(false)
  setContent {
    val animSize = remember { Animatable(48.dp, Dp.VectorConverter) }

    Box(
      contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
      Box(modifier = Modifier
        .size(animSize.value)
        .background(Color.Green, RoundedCornerShape(8.dp))
        .clickable {
          big = !big
        })

      // TweenSpec
      LaunchedEffect(key1 = big, block = {
        // animSize.animateTo(if (big) 200.dp else 100.dp, TweenSpec(easing = LinearEasing))
        // animSize.animateTo(if (big) 200.dp else 100.dp, TweenSpec(easing = FastOutSlowInEasing))
        // animSize.animateTo(if (big) 200.dp else 100.dp, TweenSpec(easing = FastOutLinearInEasing))
        // animSize.animateTo(if (big) 200.dp else 100.dp, TweenSpec(easing = LinearOutSlowInEasing))

        // 自定义动画完成度曲线
        /*animSize.animateTo(if (big) 200.dp else 100.dp, tween(easing = {
          it // 返回动画完成度 动画函数曲线(0.0f ~ 1.0f)
        }))*/

        // 自定义贝塞尔曲线
        animSize.animateTo(
          if (big) 300.dp else 100.dp,
          tween(easing = CubicBezierEasing(1f, -1.34f, 0f, 2.13f))
        )
      })
    }
  }
}