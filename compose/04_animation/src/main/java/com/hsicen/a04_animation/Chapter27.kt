package com.hsicen.a04_animation

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.exponentialDecay
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
import androidx.compose.ui.unit.max

/******====== 27. DecayAnimationSpec ======******/
/**
 * animateDecay 惯性衰减
 *  消散型动画，即由初速度开始慢慢减速，最终停下；
 *  最常⻅的场景是滑动列表，也可以⽤于⼀切的消散和衰减，例如转盘逐渐停下的效果
 *
 * 和 Animatable.animateTo() + LinearOutSlowIn 的区别：
 *    1. animateDecay() 的初始速度是精确指定的
 *    2. animateDecay() 的终点是⾃动计算的，⽽不能⼿动指定
 * 总结：本质区别在于， animateTo() 瞄准的是⽬标值； animateDecay() 瞄准的是初始速度
 *
 * 用法和原理:
 *  anim.animateDecay(initialVelocity, animationSpec)
 *  初速度：initialVelocity
 *   1. ⽤ VelocityTracker 计算，和传统 View 系统的 VelocityTracker ⼏乎⼀样的⽤法；
 *   2. 单位是 单位 / 秒
 *
 *  速度曲线配置：animationSpec
 *  类型是 DecayAnimationSpec ，具体有 3 个函数可⽤：
 *   1. splineBasedDecay() / rememberSplineBasedDecay()：基于样条曲线的速度模型
 *      -完全复制原⽣ ListView / ScrollView / RecyclerView 的算法，即 OverScroller 的算法
 *      -由于带有针对 DPI 的强制修复，所以只能针对单位为像素值的移动或放缩动画的配置，否则会出现动画显示的问题
 *   2. exponentialDecay() ：基于指数衰减的速度模型
 *      -是原⽣没有的，Compose 新创的模型，和 splineBasedDecay() 的曲线不⼀样；
 *      -参数可配置：frictionMultiplier(摩擦力系数)，absVelocityThreshold(速度阀值，低于这个值时动画结束)
 *      -由于没有针对 DPI 的强制修复，再加上同样是基于泛型的，所以可以针对任何类型的动画做配置，适⽤范围更⼴
 */
fun ComponentActivity.composeAnimation14() {
  var big by mutableStateOf(false)

  setContent {
    val anim = remember { Animatable(0.dp, Dp.VectorConverter) }

    Box(
      contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxSize()
    ) {
      Box(
        modifier = Modifier
          .padding(0.dp, max(anim.value, 0.dp), 0.dp, 0.dp)
          .size(100.dp)
          .background(Color.Green)
          .clickable {
            if (anim.isRunning) return@clickable
            big = !big
          }
      )

      // 指数衰减 非像素修订，值是多少，动画应用的值就是多少
      val spec1 = remember { exponentialDecay<Dp>(3f, 3f) }

      // 惯性衰减 强制像素修订(DPI)，即使我们使用的是 Dp 值，也会根据当前屏幕的像素密度，计算出一个强制修正的值
      val density = LocalDensity.current
      val spec2 = remember(density) { splineBasedDecay<Dp>(density) } // overscroller 惯性滑动
      val spec3 = rememberSplineBasedDecay<Dp>()

      LaunchedEffect(big, block = {
        anim.animateDecay((if (big) (-2500).dp else 2500.dp), spec1) {
          Log.d("hsc", "composeAnimation14: ${anim.value}")
        }
      })
    }
  }
}