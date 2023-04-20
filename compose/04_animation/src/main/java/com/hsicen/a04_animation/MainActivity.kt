package com.hsicen.a04_animation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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

    composeAnimation13()
  }

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