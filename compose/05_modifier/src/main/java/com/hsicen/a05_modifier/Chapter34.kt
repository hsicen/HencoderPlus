package com.hsicen.a05_modifier

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/******====== 34. modifier:Modifier = Modifier 的含义 ======******/
/**
 * Modifier 的 companion object[Modifier.Companion]:
 *  ⼀个最基本、最简单、⽆任何效果的 Modifier。
 *
 *  作⽤⼀：作为 Composable 函数的 Modifier 参数的默认值；
 *  作⽤⼆：作为 Modifier.xxx() 函数的起始对象。
 */
fun ComponentActivity.composeModifier01() {
  setContent {
    ModifierMean01(Modifier.size(200.dp))
  }
}

@Composable
private fun ModifierMean01(modifier: Modifier = Modifier) {
  Box(modifier.background(Color.Green)) {
    Text("Content")
  }
}
