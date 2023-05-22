package com.hsicen.a04_animation

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/******====== 33. AnimatedContent ======******/
/**
 * 作用：
 *  ⽤动画的⽅式来呈现界⾯中组件的交替（出现和消失）
 *  和 Crossfade() 的区别在于，可以配置所有的动画细节
 *
 * 用法：
 *  把 Crossfade 换成 AnimatedContent
 *
 * transitionSpec 参数和 ContentTransform：
 *  ⽤来配置动画的细节，包括：出场动画、⼊场动画、遮盖关系、尺⼨动画
 *
 * ⼊场动画和出场动画：
 *  ⽤ EnterTransition 和 ExitTransition；
 *  只是需要⽤ with() 函数连接起来，创建⼀个 ContentTransform 对象：
 *    fadeIn() + scaleIn() with fadeOut()
 *
 * 遮盖关系:
 *  ⽤的是 ContentTransform 的 targetContentZIndex 参数。
 *
 *  把需要被遮住的⼊场组件的 targetContentZIndex 降低，
 *  或者把需要盖住别⼈的出场组件的 targetContentZIndex 提前升⾼，
 *  可以打破「后绘制的盖住先绘制的」的规律。
 *
 * 尺⼨动画：
 *  ⽤ SizeTransform 对象来配置；
 *  除了可以提供⼀个 FiniteAnimationSpec 对象来配置动画的速度曲线之外，
 *  还可以⽤ clip 参数来配置是否做裁切（默认是裁切）
 */
fun ComponentActivity.composeAnimation25() {
  setContent {
    AnimatedContent02()
  }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedContent01() {
  var show by remember { mutableStateOf(false) }

  Column {
    AnimatedContent(show, label = "AnimatedContent01") {
      if (it) {
        Box(
          modifier = Modifier
            .size(96.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Green)
        )
      } else {
        Box(
          modifier = Modifier
            .size(96.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Red)
        )
      }
    }

    Button(
      onClick = {
        show = !show
      }, modifier = Modifier.padding(top = 16.dp)
    ) {
      Text(text = "Change")
    }
  }
}

// 入场和出场动画细节配置
@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedContent02() {
  var show by remember { mutableStateOf(false) }

  Column {
    AnimatedContent(show, label = "AnimatedContent01",
      transitionSpec = {
        // 让 targetState 始终被遮盖
        if (targetState) {
          (fadeIn(tween(3000)) with
            fadeOut(tween(3000, delayMillis = 3000))).apply {
            targetContentZIndex = -1f
          }
        } else {
          (fadeIn(tween(3000)) with
            fadeOut(tween(3000, delayMillis = 3000)) using
            SizeTransform(false)
            ).apply {

            }
        }
      }
    ) {
      if (it) {
        Box(
          modifier = Modifier
            .size(132.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.Green)
        )
      } else {
        Box(
          modifier = Modifier
            .size(96.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Red)
        )
      }
    }

    Button(
      onClick = {
        show = !show
      }, modifier = Modifier.padding(top = 16.dp)
    ) {
      Text(text = "Change")
    }
  }
}