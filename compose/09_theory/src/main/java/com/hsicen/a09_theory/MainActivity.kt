package com.hsicen.a09_theory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hsicen.a09_theory.ui.theme.HencoderPlusTheme

/******====== 71.Compose 的原理解析 ======******/
/**
 * ComponentActivity.setContent()：
 *    1.创建出基本的上下⽂环境；
 *    2.创建出后台循环等待的协程，并订阅变量的修改事件，在变量发⽣修改之后触发局部的重组，进⽽触发刷新。
 *
 * Layout()：
 *    1.创建出 LayoutNode 对象；
 *    2.将 LayoutNode 对象插⼊到 SlotTable；
 *    3.将排列好的 LayoutNode 插⼊到 LayoutNode 树，完成组合过程。
 *
 * Snapshot：
 *    1.Compose ⾥对于变量变化的管理机制，可以让变量改变的时候发出通知、触发重组，并且在重组时应⽤到正确的变量值；
 *    2.⽀持多线程并发写⼊新值。
 *
 * SlotTable：
 *    1.Compose 内部对LayoutNode 树、Modifier等属性、⽤到的变量进⾏存储的数据结构，由于是由⼀维数组来进⾏管理，因此性能⾮常强。
 *    2.最终的 LayoutNode 树不是由 SlotTable 承载的，SlotTable 只⽤于中间计算过程。
 *
 * ComposeView
 *    AndroidComposeView
 *      LayoutNode
 *        LayoutNode
 *      LayoutNode
 *      LayoutNode
 *      LayoutNode
 */
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // decorView: FrameLayout
    //    LinearLayout
    //      R.id.content
    setContent {
      Greeting(name = "Compose")
    }
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    fontSize = 25.sp,
    modifier = modifier
      .padding(16.dp)
      .background(Color.Yellow)
      .padding(16.dp)
      .clickable {
        println("@@@ click from mouse.")
      }
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  HencoderPlusTheme {
    Greeting("Android")
  }
}