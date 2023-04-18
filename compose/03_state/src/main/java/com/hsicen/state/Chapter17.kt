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
 *
 * 具有函数穿透功能的局部变量 ==> 作用相当于函数参数 ==> 加强版的函数参数，不需要显式传递的函数参数
 * 变量 -> 函数参数 -> CompositionLocal
 *
 * CompositionLocal 是什么?
 *  1. 表⾯上看，是「针对 Composition 的、可以穿透函数的局部变量」；但其实作⽤更接近「不⽤显式传递的函数参数」。
 *  2. 它和函数参数的区别：
 *    - 函数参数是由下⽽上：函数负责规定参数的⽤途，调⽤者负责按规定来填写。
 *    - CompositionLocal 是由上⽽下： CompositionLocal ⾃身规定出⾃⼰的⽤途，具体的提供者和使⽤者各⾃按这份规定来提供和使⽤。
 *
 * 适用场景
 *  上下文 / 环境 / 主题
 *  外面给的 -> CompositionLocal
 *  里面要的 -> 函数参数
 *
 * 用法
 *  1. 创建:
 *    val name = compositionLocalOf<T> { error("没有提供值") }
 *    val name = staticCompositionLocalOf<T> { error("没有提供值") }
 *
 *    最好以 local- 或 Local- 打头
 *    compositionLocalOf 和 staticCompositionLocalOf 的区别在于：
 *      1.compositionLocalOf() 会精准订阅、值改变时精准 Recompose，所以性能的消耗在于订阅阶段；
 *      2.staticCompositionLocalOf() 不订阅、值改变时全量 Recompose，所以性能的消耗在于更新阶段；
 *      3.compositionLocalOf() 更适合「值可会改变」的场景，
 *        staticCompositionLocalOf() 适合「值不会改变或⼏乎不会改变」的场景
 *
 *  2. 提供：
 *    CompositionLocalProvider( LocalXXX provides xxx) {
 *      xxxx
 *    }
 *
 *    可以⽤逗号 , 来分隔多个 LocalXxx provides xxx 。
 *
 *  3. 使用：
 *    LocalXxx.current
 *    CompositionLocal.current 的 get() 是个 Composable 函数，因此它需要在Composable 环境⾥使⽤
 *
 * 其它
 *  1. CompositionLocal 可以穿透多层函数
 *  2. 可以嵌套多个 CompositionLocalProvider() 来提供同⼀个 CompositionLocal 使⽤，多层之间互不⼲扰；
 *  3. 可以设置默认值，但「没有有意义的默认」的时候不要强⾏设置，应该抛异常，更有利于开发调试。
 *
 *  外面给的？ -> 上下文
 *  里面要的？ -> 函数参数
 *
 *  文字颜色？ 上下文或函数参数都行  没有上下文和函数参数只能选其一的观念
 */
fun ComponentActivity.compositionLocal1701() {
  @Composable
  fun User(name: String) {
    Text(text = name)
  }

  setContent {
    val name = "hsicen" // local variable
    User(name)
  }
}

private val localName = compositionLocalOf<String> { error("name 没有提供值") /*提供默认值*/ }
fun ComponentActivity.compositionLocal1702() {
  @Composable
  fun User() {
    // 从内部获取有穿透能力的数据
    Text(
      localName.current,
      modifier = Modifier
        .padding(16.dp)
        .background(MaterialTheme.colors.background)
    )
    LocalContext.current  // AndroidComposeView.getContext()
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
      // TextWithBackground() 没有初始值异常
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