package com.hsicen.state

import android.os.Bundle
import android.os.SystemClock
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
class Animation : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    composeAnimation4()
  }

  private fun composeAnimation() {

    setContent {
      var size by remember { mutableStateOf(48.dp) }

      Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
      ) {
        Box(
          modifier = Modifier
            .size(size)
            .background(Color.Green)
            .clickable {
              // 触发 Recompose
              if (SystemClock.uptimeMillis() % 3 != 0L) {
                size += 20.dp
              } else {
                size -= 20.dp
              }
            }
        )
      }
    }
  }

  /**
   * 1. 没法手动设置动画的初始值，默认当前值为初始值
   */
  private fun composeAnimation1() {
    var size by mutableStateOf(100.dp)

    setContent {
      // val animSize by  remember { animateDpAsState(48.dp) } // State not mutableState
      val animSize by animateDpAsState(size) // State, just getValue

      Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
      ) {
        Box(
          modifier = Modifier
            .size(animSize)
            .background(Color.Green)
            .clickable {
              if (SystemClock.uptimeMillis() % 3 != 0L) {
                size += 20.dp
              } else {
                size -= 20.dp
              }
            }
        )
      }
    }
  }

  /**** Animatable ****/
  private fun composeAnimation2() {
    setContent {
      val animSize = remember { Animatable(48.dp, Dp.VectorConverter) }

      Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
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
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
      ) {
        Box(
          modifier = Modifier
            .size(animSize.value)
            .background(Color.Green)
            .clickable {
              big = !big
            }
        )

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
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
      ) {
        Box(
          modifier = Modifier
            .size(animSize.value)
            .background(Color.Green)
            .clickable {
              big = !big
            }
        )

        // Recompose 优化  remember(key1)
        LaunchedEffect(key1 = big, block = {
          animSize.snapTo(if (big) 300.dp else 0.dp) // 动画起始值
          animSize.animateTo(if (big) 200.dp else 100.dp) // 动画平缓过渡
        })
      }
    }
  }

}