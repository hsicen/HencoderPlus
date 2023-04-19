package com.hsicen.a04_animation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FloatSpringSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.SnapSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.spring
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
 */
class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    composeAnimation07()
  }

  /******====== 1.4 AnimationSpec - SnapSpec ======******/
  /**
   * SnapSpec/snap - 可以设置延时
   * snapTo
   */
  private fun composeAnimation7() {
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

  /******====== 1.5 AnimationSpec - KeyframesSpec ======******/
  /**
   *  KeyframesSpec 是分段的 TweenSpec
   */
  private fun composeAnimation8() {
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
          animSize.animateTo(if (big) 200.dp else 48.dp, keyframes {
            durationMillis = 1000
            delayMillis = 500

            48.dp at 0 with LinearEasing
            144.dp at 150
            20.dp at 300 with FastOutSlowInEasing
          })
        })
      }
    }
  }

  /******====== 1.6 AnimationSpec - SpringSpec ======******/
  /**
   * SpringSpec
   *  dampingRatio: 阻尼比，控制动画有多Q弹
   *  stiffness: 刚度，控制动画回弹速度，即弹簧「有多硬 / 多想弹回去」
   *  visibilityThreshold: 可见阈值，控制动画可见阈值 (防止过大或过小)，直接判断弹簧可以停⽌的阈值
   */
  private fun composeAnimation9() {
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
          animSize.animateTo(48.dp, spring(0.1f, Spring.StiffnessMedium), 2000.dp)
        })
      }
    }
  }

  /******====== 1.7 AnimationSpec - RepeatableSpec ======******/
  /**
   * RepeatableSpec
   *  iterations: 动画重复次数
   *  animation: 要重复的动画
   *  repeatMode: 重复的方式
   *  initialStartOffset: 初始时间偏移
   */
  private fun composeAnimation10() {
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
          // animSize.animateTo(if (big) 200.dp else 48.dp, repeatable(3, tween()))
          // animSize.animateTo(if (big) 200.dp else 48.dp, repeatable(3, tween(), RepeatMode.Reverse))
          // animSize.animateTo(if (big) 200.dp else 48.dp, repeatable(3, tween(), RepeatMode.Reverse, StartOffset(500, StartOffsetType.Delay)))
          animSize.animateTo(if (big) 200.dp else 48.dp, repeatable(3, tween(), RepeatMode.Reverse, StartOffset(300, StartOffsetType.FastForward)))
        })
      }
    }
  }

  /******====== 1.8 AnimationSpec - InfiniteRepeatableSpec ======******/
  /**
   * 循环次数是无限的
   */
  private fun composeAnimation11() {
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
          // animSize.animateTo(if (big) 200.dp else 48.dp, infiniteRepeatable(tween()))
          // animSize.animateTo(if (big) 200.dp else 48.dp, infiniteRepeatable(tween(), RepeatMode.Reverse))
          // animSize.animateTo(if (big) 200.dp else 48.dp, infiniteRepeatable(tween(), RepeatMode.Reverse, StartOffset(500, StartOffsetType.Delay)))
          animSize.animateTo(if (big) 200.dp else 48.dp, infiniteRepeatable(tween(), RepeatMode.Reverse, StartOffset(300, StartOffsetType.FastForward)))
        })
      }
    }
  }

  /******====== 1.9 AnimationSpec - FloatAnimationSpec ======******/
  private fun composeAnimation12() {
    var big by mutableStateOf(false)

    setContent {
      val animSize = remember { Animatable(100f) }

      Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
      ) {
        Box(modifier = Modifier
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
  private fun composeAnimation13() {
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