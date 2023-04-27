package com.hsicen.a04_animation

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationEndReason
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/******====== 29. 动画的边界限制、结束和取消 ======******/
/**
 * 其实就是动画在没有播放完成之后就被结束了，具体的场景：
 *  1. 旧的动画被新的动画停⽌；
 *  2. 动画被 stop() 停⽌；
 *  3. 触达边界导致的停⽌。
 *
 * 旧动画被新动画停⽌(异常结束)：
 *  1. 对同⼀个 Animatable 调⽤两次动画：旧动画停⽌；
 *  2. 抛异常 CancellationException。
 *
 * ⽤ stop() 函数停⽌动画(异常结束)：
 *  1. 在协程⾥调⽤ Animatable.stop() ，可以停⽌进⾏中的动画；
 *  2. 抛异常 CancellationException。
 *
 * 触达边界导致的停⽌(正常结束)：
 *  1. ⽤ updateBounds() 可以设置边界，动画触达边界后会停⽌；
 *  2. 不会抛异常；
 *  3. 多维动画只要触达任何⼀个维度的上下界，就会停⽌；
 *  4. 反弹：触达边界停⽌后，反向发射新的动画；
 *  5. 反弹 2：⾃⼰写反弹算法——更精确，也更复杂
 */
fun ComponentActivity.composeAnimation16() {
  var big by mutableStateOf(false)

  setContent {
    val anim = remember { Animatable(0.dp, Dp.VectorConverter) }
    val animY = remember { Animatable(0.dp, Dp.VectorConverter) }

    BoxWithConstraints(contentAlignment = Alignment.TopStart, modifier = Modifier.fillMaxSize()) {
      Box(
        modifier = Modifier
          .padding(anim.value, animY.value, 0.dp, 0.dp)
          .size(100.dp)
          .background(Color.Green)
          .clickable {
            if (anim.isRunning) return@clickable
            big = !big
          }
      )

      val spec1 = remember { exponentialDecay<Dp>() }
      val spec2 = remember { exponentialDecay<Dp>() }

      LaunchedEffect(big, block = {
        // delay(1000)
        var result = anim.animateDecay((if (big) (-4000).dp else 4000.dp), spec1) {
          // 动画每一帧刷新都会回调
          Log.d("hsc", "composeAnimation16: ${anim.value}")
        }

        while (result.endReason == AnimationEndReason.BoundReached) {
          result = anim.animateDecay(-result.endState.velocity, spec1)
        }

        big = !big
      })

      // 打断动画的执行
      LaunchedEffect(big) {
        // delay(1200)
        // anim.animateTo(300.dp, tween())  新动画打断
        // anim.stop() stop 打断

        animY.animateDecay((if (big) (-2000).dp else 2000.dp), spec2) {
          // 动画每一帧刷新都会回调
          Log.d("hsc", "composeAnimation16: ${animY.value}")
        }
      }

      anim.updateBounds(upperBound = maxWidth - 100.dp, lowerBound = 0.dp) // 边界触达
      animY.updateBounds(upperBound = maxHeight - 100.dp, lowerBound = 0.dp) // 边界触达
    }
  }
}
