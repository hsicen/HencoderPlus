package com.hsicen.a04_animation

import android.os.Bundle
import android.os.SystemClock
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.*
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
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

    composeAnimation13()
  }

  /******====== 1.1 状态转移型动画 - animateXXXAsState ======******/
  /**
   * 1. animateXXXAsState 返回的是一个 State 对象，而不是 MutableState，不能够改变；能够监听值的变化，触发 Recompose
   * 2. animateXXXAsState 动画渐变目标值参数是可改变的，通过改变这个值来实现动画效果
   * 3. 没法手动设置动画的初始值，默认当前值为初始值
   */
  private fun composeAnimation() {

    setContent {
      var size by remember { mutableStateOf(48.dp) }

      Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
      ) {
        Box(modifier = Modifier
          .size(size)
          .background(Color.Green)
          .clickable {
            // 触发 Recompose
            if (SystemClock.uptimeMillis() % 3 != 0L) {
              size += 20.dp
            } else {
              size -= 20.dp
            }
          })
      }
    }
  }

  private fun composeAnimation1() {
    var size by mutableStateOf(100.dp)

    setContent {
      // val animSize by  remember { animateDpAsState(48.dp) } // State not mutableState
      val animSize by animateDpAsState(size) // State, just getValue

      Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
      ) {
        Box(modifier = Modifier
          .size(animSize)
          .background(Color.Green)
          .clickable {
            if (SystemClock.uptimeMillis() % 3 != 0L) {
              size += 20.dp
            } else {
              size -= 20.dp
            }
          })
      }
    }
  }

  /******====== 1.2 流程定制型动画 - Animatable ======******/
  /**
   * Animatable
   *  1. 可以实现动画流程细节的定制，animatableXXXAsState 只针对状态转移的动画场景
   *  2. Animatable 是 animatableXXXAsState 的底层实现
   *
   * TwoWayConverter
   *  用于把属性动画和 Compose 内部的 AnimationVector1D/2D/3D/4D 做转化计算用；
   *  常用的类型(如 Float, Dp, Size 等) 可以直接使用系统自带的 Dp.VectorConverter, Float.VectorConverter 等
   *  特殊类型的属性需要自定义 TwoWayConverter
   *
   * LaunchedEffect
   *  用于启动协程
   *  和普通启动协程的方法(如 lifecycleScope.launch )的区别：
   *   1. 和 Compose 做了结合，不会在 Recompose 过程中发生意外重启
   *   2. 可以填写一个或多个 keyN 参数，用于在需要的时候主动重启协程
   */
  private fun composeAnimation2() {
    setContent {
      val animSize = remember { Animatable(48.dp, Dp.VectorConverter) }

      Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
      ) {
        Box(
          modifier = Modifier
            .size(animSize.value)
            .background(Color.Green)
        )

        // Recompose 优化  remember(key1)
        LaunchedEffect(key1 = Unit, block = {
          delay(1000)
          animSize.animateTo(200.dp)
        })
      }
    }
  }

  private fun composeAnimation3() {
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

  private fun composeAnimation4() {
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
          animSize.snapTo(if (big) 300.dp else 0.dp) // 动画起始值
          animSize.animateTo(if (big) 200.dp else 100.dp, spring(Spring.DampingRatioMediumBouncy)) // 动画平缓过渡
        })
      }
    }
  }

  /******====== DurationBasedAnimationSpec ======******/
  /******====== 1.3 AnimationSpec - TweenSpec ======******/
  /**
   * TweenSpec
   *  LinearEasing  - 匀速动画
   *  FastOutSlowInEasing - 元素发生变化， A 状态到 B 状态的动画
   *  LinearOutSlowInEasing - 入场动画
   *  FastOutLinearInEasing - 出场动画
   *
   *  Inbetween 补帧
   *  TweenSpec
   *  tween
   */
  private fun composeAnimation5() {
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
          animSize.animateTo(if (big) 200.dp else 100.dp, spring(Spring.DampingRatioMediumBouncy))
        })
      }
    }
  }

  private fun composeAnimation6() {
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

        // TweenSpec
        LaunchedEffect(key1 = big, block = {
          // animSize.animateTo(if (big) 200.dp else 100.dp, TweenSpec(easing = LinearEasing))
          // animSize.animateTo(if (big) 200.dp else 100.dp, TweenSpec(easing = FastOutSlowInEasing))
          // animSize.animateTo(if (big) 200.dp else 100.dp, TweenSpec(easing = FastOutLinearInEasing))
          // animSize.animateTo(if (big) 200.dp else 100.dp, TweenSpec(easing = LinearOutSlowInEasing))
          animSize.animateTo(if (big) 200.dp else 100.dp, tween(easing = Easing {
            it // 返回动画完成度 动画函数曲线(0.0f ~ 1.0f)
          }))
        })
      }
    }
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