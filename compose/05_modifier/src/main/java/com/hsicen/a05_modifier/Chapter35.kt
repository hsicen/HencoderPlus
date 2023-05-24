package com.hsicen.a05_modifier

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/******====== 35. Modifier.then(), CombinedModifier 和 Modifier.Element ======******/
/**
 * Modifier.then() 和 CombinedModifier
 *   1. then()：创建 CombinedModifier 来合并两个 Modifier；
 *   2. Modifier.Companion.then() 有更简化的实现，但本质上没有超出「合并」的概念。
 *
 *   3. CombinedModifier：把两个 Modifier 的效果合到⼀起。
 *   4. Modifier.foldIn() 、 Modifier.foldOut() 、 Modifier.any() 、 Modifier.all()
 *      实际上都是为了 CombinedModifier 的合并功能⽽创建的，虽然它们是 Modifier 接⼝的函数。
 *
 * Modifier.Element
 *   1. 是所有 Modifier（除了 Modifier.Companion 和 CombinedModifier ）的直接或间接⽗接⼝。
 *   2. 依然是为了 CombinedModifier ⽽创建的，作⽤是提供⼀套统⼀的、
 *      和 CombinedModifier 不⼀样的「普通 Modifier 」的 Modifier 基本实现。
 *
 *  Modifier.Element 和 Modifier.Companion 是 CombinedModifier(Modifier引用链条) 的递归终止条件
 */
fun ComponentActivity.composeModifier02() {
  setContent {
    ModifierThen()
  }
}

@Composable
private fun ModifierThen(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .size(100.dp)
      .background(Color.Green)
      .size(200.dp)
      .background(Color.Blue)
  )

  listOf("1", "2", "3").any { it == "2" } // true
  listOf("1", "2", "3").all { it.length > 2 } // false

  Modifier.then(modifier)
}