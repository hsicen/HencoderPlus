package com.hsicen.a04_animation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FloatSpringSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.animation.splineBasedDecay
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/**
 * @author: hsicen
 * @date: 2022/3/28 10:07
 * @email: codinghuang@163.com
 * description:
 *
 * animateXXXAsState 动画 -> 对动画的目标值进行控制，适用于状态切换
 * Animatable 动画 -> 对动画过程进行控制，适用于动画流程定制
 *
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
class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    composeAnimation11()
  }

  /******====== 1.8 AnimationSpec - InfiniteRepeatableSpec ======******/
  /**
   * 循环次数是无限的
   */
  private fun composeAnimation12() {
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
          // animSize.animateTo(if (big) 200.dp else 48.dp, infiniteRepeatable(tween()))
          // animSize.animateTo(if (big) 200.dp else 48.dp, infiniteRepeatable(tween(), RepeatMode.Reverse))
          // animSize.animateTo(if (big) 200.dp else 48.dp, infiniteRepeatable(tween(), RepeatMode.Reverse, StartOffset(500, StartOffsetType.Delay)))
          animSize.animateTo(if (big) 200.dp else 48.dp, infiniteRepeatable(tween(), RepeatMode.Reverse, StartOffset(300, StartOffsetType.FastForward)))
        })
      }
    }
  }

  /******====== 1.9 AnimationSpec - FloatAnimationSpec ======******/
  private fun composeAnimation13() {
    var big by mutableStateOf(false)

    setContent {
      val animSize = remember { Animatable(100f) }

      Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
      ) {
        Box(
          modifier = Modifier
            .size(animSize.value.dp)
            .background(Color.Green)
            .clickable {
              big = !big
            })

        LaunchedEffect(key1 = big, block = {
          // animSize.animateTo(if (big) 300f else 100f, FloatTweenSpec())
          animSize.animateTo(if (big) 300f else 100f, FloatSpringSpec())
        })
      }
    }
  }

  /******====== 1.10 VectorizedAnimationSpec ======******/

  /******====== 1.11 DecayAnimationSpec ======******/
  /**
   * animateDecay 惯性衰减
   *  从初始速度慢慢的停下来，不要求目标值，作用于松手后的惯性滑动
   */
  private fun composeAnimation14() {
    setContent {
      val anim = remember { Animatable(0.dp, Dp.VectorConverter) }

      Box(
        contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxSize()
      ) {
        Box(
          modifier = Modifier
            .padding(0.dp, anim.value, 0.dp, 0.dp)
            .size(100.dp)
            .background(Color.Green)
        )

        exponentialDecay<Dp>()
        splineBasedDecay<Dp>(LocalDensity.current) // overscroller 惯性滑动
        val spec = rememberSplineBasedDecay<Dp>()

        LaunchedEffect(Unit, block = {
          delay(1000)
          anim.animateDecay(3000.dp, spec)
        })
      }
    }
  }

}