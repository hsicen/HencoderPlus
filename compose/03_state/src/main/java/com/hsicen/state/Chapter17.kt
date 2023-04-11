package com.hsicen.state

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


/****=== 17.CompositionLocal ===****/
/**
 * State hoisting 状态提升 -> 有内部状态到无内部状态
 * CompositionLocal: composition 的局部变量
 * 具有函数穿透功能的局部变量 ==> 作用相当于函数参数 ==> 加强版的函数参数，不需要显式传递的函数参数
 * 变量 -> 函数参数 -> CompositionLocal
 *
 * 声明成一个不会造成更大影响范围的对象
 *
 * 函数参数 -> 自下而上，函数内部需要的
 * CompositionLocal -> 自上而下，外面给的
 *
 * 上下文 / 环境 / 主题
 * 外面给的 -> CompositionLocal
 * 里面要的 -> 函数参数
 *
 * 文字的颜色
 *
 * CompositionLocal 是可以嵌套的
 *
 * compositionLocalOf   会跟踪使用记录，当前值失效时，使用了这个值的地方会被 Recompose  -> 精准刷新(适用于频繁刷新的内容)
 * staticCompositionLocalOf  不会跟踪使用记录，但是当 当前值失效时，会进行完整的重组  -> 全量刷新(适用于偶尔刷新的内容)
 */
fun ComponentActivity.compositionLocal1701() {
  @Composable
  fun User(name: String) {
    Text(text = name)
  }

  setContent {
    val name = "hsicen" // local variable
    User(name = name)
  }
}

private val localName = compositionLocalOf<String> { error("name 没有提供值") }
fun ComponentActivity.compositionLocal1702() {
  @Composable
  fun User() {
    // 从内部获取有穿透能力的数据
    Text(localName.current)
    LocalContext.current  // AndroidComposeView.getContext()
    MaterialTheme.colors.background
  }

  setContent {
    // 从外部提供有穿透能力的数据
    val name = "hsicen" // local variable
    CompositionLocalProvider(localName provides name) {
      User()
    }
  }
}

private val LocalBackground = compositionLocalOf<Color> { error("颜色 没有提供颜色值") }
fun ComponentActivity.compositionLocal1703() {
  @Composable
  fun TextWithBackground() {
    Text("有背景的文字", Modifier.background(LocalBackground.current))
  }

  setContent {
    Column {
      CompositionLocalProvider(LocalBackground provides Color.Yellow) {
        TextWithBackground()
        CompositionLocalProvider(LocalBackground provides Color.Blue) {
          TextWithBackground()
        }
        TextWithBackground()
      }
    }
  }
}

fun ComponentActivity.compositionLocal1704() {
  @Composable
  fun TextWithBackground() {
    Text("有背景的文字", Modifier.background(LocalBackground.current))
  }

  var specialColor by mutableStateOf(Color.Gray)
  setContent {
    Column(modifier = Modifier.padding(16.dp)) {
      CompositionLocalProvider(LocalBackground provides specialColor) {
        TextWithBackground()
        CompositionLocalProvider(LocalBackground provides Color.Blue) {
          TextWithBackground()
        }
        TextWithBackground()

        Button(onClick = {
          specialColor = Color.Magenta
        }) {
          Text("改变颜色")
        }
      }
    }
  }
}