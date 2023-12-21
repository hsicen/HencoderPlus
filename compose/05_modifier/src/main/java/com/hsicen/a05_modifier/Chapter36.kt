package com.hsicen.a05_modifier

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/******====== 36. Modifier.composed() 和 ComposedModifier ======******/
/**
 * 用法：Modifier.composed {} ，在 compose 时，通过⼯⼚函数⾥创建 Modifier 并返回。
 *      在每一个使用处都会创建出独立的 Modifier 实例
 *      有状态的 Modifier 实例
 *
 * 用处/应用场景：
 *   1. ⽤于⾃定义的 Modifier
 *   2. 需要 Composable 上下⽂。包括：
 *     1. 对于⾃定义 Modifier 有「状态」的需求，因此需要 remember {} 或者 rememberXXX {} 函数来配合；
 *     2. 需要调⽤ LaunchedEffect {} 来使⽤协程；
 *     3. 需要调⽤ CompositionLocal.current 来获取 CompositionLocal 对象；
 *     4. 其他需要 Composable 上下⽂的场景
 */
fun ComponentActivity.composeModifier03() {
  setContent {
    ModifierCompose()
  }
}

@Composable
private fun ModifierCompose() {
  // 状态不会共享，compose 过程中才会创建这个 Modifier
  val modifier = Modifier.composed {
    // Modifier.padding(12.dp) // 无状态的 Modifier

    var padding by remember { mutableStateOf(60.dp) } // 防止重复初始化
    Modifier // 有状态的 Modifier
      .padding(padding)
      .clickable {
        padding = 0.dp
      }
  }

  // 状态共享
  var padding by remember { mutableStateOf(60.dp) }
  val modifier1 = Modifier
    .padding(padding)
    .clickable {
      padding = 0.dp
    }

  Column {
    Column {
      Box(Modifier.background(Color.Blue) then modifier)
      Text(text = "hsicen modifier", Modifier.background(Color.Green) then modifier)
    }

    Spacer(Modifier.size(16.dp))

    Column {
      Box(Modifier.background(Color.Blue) then modifier1)
      Text(text = "hsicen modifier1", Modifier.background(Color.Green) then modifier1)
    }
  }
}

// use  remember {  }
private fun Modifier.paddingJump(dp: Dp) = composed {
  var padding by remember { mutableStateOf(dp) } // 防止重复初始化
  Modifier // 有状态的 Modifier
    .padding(padding)
    .clickable {
      padding = 0.dp
    }
}

// use coroutine
private fun Modifier.coroutineModifier() = composed {
  LaunchedEffect(Unit) {

  }
  Modifier
}

// use CompositionLocal
private fun Modifier.localModifier() = composed {
  LocalContext.current

  Modifier
}
