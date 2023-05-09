package com.hsicen.a04_animation

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

/******====== 31. AnimatedVisibility() ======******/
/**
 * 作用：
 *  ⽤动画的⽅式来让组件出现和消失。
 *
 * 用法：
 *  把界面里的 if 替换成 AnimatedVisibility；
 *  注意内部只能有一个组件，超过一个会出现布局异常。
 *
 * EnterTransition & ExitTransition: ⽤于在四个维度调节动画的属性：透明度、位移、裁切、放缩
 *  - 透明度：fadeIn()、 fadeOut()
 *  - 位移：slideIn() 、  slideInHorizontally() 、  slideInVertically() 、
 *         slideOut() 、 slideOutHorizontally() 、 slideOutVertically()
 *  - 裁切：expandIn() 、  expandHorizontally() 、 expandVertically() 、
 *         shrinkOut() 、 shrinkHorizontally() 、 shrinkVertically()
 *  - 缩放：scaleIn() 、 scaleOut()
 *
 * EnterTransition. +：
 *  把两个 EnterTransition 合成⼀个，优先取左边的属性，任何属性只有左边没配置的情况下才会使⽤右边的。
 */
fun ComponentActivity.composeAnimation23() {

  setContent {
    TransitionSquare06()
  }
}

@Composable
private fun TransitionSquare01() {
  var big by remember { mutableStateOf(false) }
  val updateTransition = updateTransition(big, label = "big")
  val size by updateTransition.animateDp(label = "size") { if (it) 200.dp else 80.dp }
  val corner by updateTransition.animateDp(label = "corner") { if (it) 18.dp else 0.dp }

  Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
    AnimatedVisibility(big) {
      Box(
        modifier = Modifier
          .size(size)
          .clip(RoundedCornerShape(corner))
          .background(Color.Green)
      )
    }

    Button(
      onClick = {
        big = !big
      }, modifier = Modifier
        .padding(top = 400.dp)
    ) {
      Text(text = "Change")
    }
  }
}


@Composable
private fun TransitionSquare02() {
  var big by remember { mutableStateOf(false) }
  val updateTransition = updateTransition(big, label = "big")
  val size by updateTransition.animateDp(label = "size") { if (it) 200.dp else 80.dp }
  val corner by updateTransition.animateDp(label = "corner") { if (it) 18.dp else 0.dp }

  Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
    AnimatedVisibility(big, enter = fadeIn(tween(2000), initialAlpha = 0.5f)) {
      Box(
        modifier = Modifier
          .size(size)
          .clip(RoundedCornerShape(corner))
          .background(Color.Green)
      )
    }

    Button(
      onClick = {
        big = !big
      }, modifier = Modifier
        .padding(top = 400.dp)
    ) {
      Text(text = "Change")
    }
  }
}


@Composable
private fun TransitionSquare03() {
  var big by remember { mutableStateOf(false) }
  val updateTransition = updateTransition(big, label = "big")
  val size by updateTransition.animateDp(label = "size") { if (it) 200.dp else 80.dp }
  val corner by updateTransition.animateDp(label = "corner") { if (it) 18.dp else 0.dp }

  Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
    AnimatedVisibility(big, enter = slideIn(tween(2000)) {
      // IntOffset(-it.width, -it.height)
      // IntOffset(0, -it.height)
      IntOffset(-it.width, 0)
    }) {
      Box(
        modifier = Modifier
          .size(size)
          .clip(RoundedCornerShape(corner))
          .background(Color.Green)
      )
    }

    Button(
      onClick = {
        big = !big
      }, modifier = Modifier
        .padding(top = 400.dp)
    ) {
      Text(text = "Change")
    }
  }
}

@Composable
private fun TransitionSquare04() {
  var big by remember { mutableStateOf(false) }
  val updateTransition = updateTransition(big, label = "big")
  val size by updateTransition.animateDp(label = "size") { if (it) 200.dp else 80.dp }
  val corner by updateTransition.animateDp(label = "corner") { if (it) 18.dp else 0.dp }

  Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
    AnimatedVisibility(big, enter = slideInHorizontally {// 返回初始便宜宽度
      // it  // it 表示内容的宽度
      -it
    }) {
      Box(
        modifier = Modifier
          .size(size)
          .clip(RoundedCornerShape(corner))
          .background(Color.Green)
      )
    }

    Button(
      onClick = {
        big = !big
      }, modifier = Modifier
        .padding(top = 400.dp)
    ) {
      Text(text = "Change")
    }
  }
}

@Composable
private fun TransitionSquare05() {
  var big by remember { mutableStateOf(false) }
  val updateTransition = updateTransition(big, label = "big")
  val size by updateTransition.animateDp(label = "size") { if (it) 200.dp else 80.dp }
  val corner by updateTransition.animateDp(label = "corner") { if (it) 18.dp else 0.dp }

  Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
    AnimatedVisibility(
      big, enter = expandIn(
        tween(5000),
        Alignment.TopStart,
        true
      ) {
        IntSize(it.width / 2, it.height / 2)
      }
    ) {
      Box(
        modifier = Modifier
          .size(size)
          .clip(RoundedCornerShape(corner))
          .background(Color.Green)
      )
    }

    Button(
      onClick = {
        big = !big
      }, modifier = Modifier
        .padding(top = 400.dp)
    ) {
      Text(text = "Change")
    }
  }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun TransitionSquare06() {
  var big by remember { mutableStateOf(false) }
  val updateTransition = updateTransition(big, label = "big")
  val size by updateTransition.animateDp(label = "size") { if (it) 200.dp else 80.dp }
  val corner by updateTransition.animateDp(label = "corner") { if (it) 18.dp else 0.dp }

  Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
    AnimatedVisibility(
      big, enter = scaleIn(
        tween(10000),
        transformOrigin = TransformOrigin(0f, 0f)
      )
    ) {
      Box(
        modifier = Modifier
          .size(size)
          .clip(RoundedCornerShape(corner))
          .background(Color.Green)
      )
    }

    Button(
      onClick = {
        big = !big
      }, modifier = Modifier
        .padding(top = 400.dp)
    ) {
      Text(text = "Change")
    }
  }
}