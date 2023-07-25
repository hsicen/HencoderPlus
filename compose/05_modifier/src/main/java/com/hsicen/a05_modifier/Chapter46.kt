package com.hsicen.a05_modifier

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/******====== 46. SemanticsModifier 的作用/写法/原理 ======******/
/**
 * 作用：
 *  1.给组件添加额外的文字信息，让无障碍系统可以识别和读出；
 *  2.也可以添加其它信息，来辅助测试系统对组件进行识别。
 *
 * 写法：
 *  1.Modifier.semantics(mergeDescendants: Boolean) { xxx = xxx }
 *    (1).mergeDescendants ：是否把内部的组件合并到⾃⼰这⾥来，同时自己不再被外部组件合并。
 *  2.Modifier.clearAndSetSemantics {} ：把⾃⼰内部的组件直接吞掉。
 *
 * 原理：
 *  1.SemanticsModifier 的处理，和 DrawModifier 、 PointerInputModifier 、 ParentDataModifier ⼀样；
 *  2.通过坐标识别组件的原理，和 PointerInputModifier 一样；
 *  3.触发：通过 AndroidComposeView.dispatchHoverEvent()。
 */
fun ComponentActivity.composeModifier08() {
  setContent {
    ModifierSemantics01()
  }
}

@Composable
private fun ModifierSemantics01() {
  Column(modifier = Modifier.padding(16.dp)) {
    Button(onClick = { /*TODO*/ }) {
      Text(text = "静夜思", fontWeight = FontWeight(800),
        modifier = Modifier.semantics(true) {

        })
    }

    Text(text = "床前明月光，疑是地上霜；")
    Text(text = "举头望明月，低头思故乡。")

    Box(
      modifier = Modifier
        .width(100.dp)
        .height(60.dp)
        .padding(top = 16.dp, bottom = 16.dp)
        .background(Color.Magenta)
        .semantics(true) {
          contentDescription = "我是大方块"
        }
    ) {
      // 小方块被合并
      Text(text = "我是小方块")
    }
  }
}

@Composable
private fun ModifierSemantics02() {
  Column(modifier = Modifier.padding(16.dp)) {
    Button(onClick = { /*TODO*/ }) {
      Text(text = "静夜思", fontWeight = FontWeight(800),
        modifier = Modifier.semantics(true) {

        })
    }

    Text(text = "床前明月光，疑是地上霜；")
    Text(text = "举头望明月，低头思故乡。")

    Box(
      modifier = Modifier
        .width(100.dp)
        .height(60.dp)
        .padding(top = 16.dp, bottom = 16.dp)
        .background(Color.Magenta)
        .clearAndSetSemantics {
          contentDescription = "我是大方块"
        }
    ) {
      // 小方块不会被读
      Text(text = "我是小方块")
    }
  }
}