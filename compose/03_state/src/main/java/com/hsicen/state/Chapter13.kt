package com.hsicen.state

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*


/******====== 13.无状态，状态提升和单向数据流 ======******/
/**
 * 状态：UI 组件的属性
 *   无状态：Stateless
 *   有状态：Stateful
 *   其实是有内部状态、⽆内部状态
 *
 * State Hoisting: 状态提升
 *   把内部状态抽到外部，即把控件由有（内部）状态变成⽆（内部）状态。⽅式是把内部状态放进函数参数⾥，让外部来调⽤
 *   「Compose 是⽆状态的」，真实含义是——Compose 的 UI 组件是⽆内部状态的，并且是「可以⽆状态」，⽽不是「绝对⽆状态」
 *   有状态、⽆状态，其实说到底，指的也不是组件本身，⽽是组件的属性。按照这个⻆度来看，⼀个多属性的组件，可以同时具有「有状态」和「⽆状态」这两种特征。
 *
 *   一个有状态的组件 -> 无状态组件  ==> 把这个组件的状态抽出来
 *   状态尽量少暴露，尽量下沉，减少出错
 *
 * TextField() -> belong material, not ui or foundation.
 * TextField -> BasicTextField
 *
 * 数据：缓存：本地数据 + 网络数据
 * 多数据来源：需要解决的问题->数据同步性
 * 解决：单数据来源
 *
 * Single Source of Truth(单一信息源)
 * Jetpack -> ViewModel -> Repository[数据库+网络]
 * Unidirectional Data Flow -> 单向数据流
 *   数据由上向下传递，事件由下向上传递，形成单向环。
 *   对于 Composable 函数来说的实现⽅式：进⾏ State Hoisting 状态提升的时候，
 *    对于有交互功能的Composable，如果提升的是和交互相关的状态，要把交互的回调也⼀起提升上去，
 *    放进函数参数⾥，来实现完整的封装。
 *
 * mutableStateOf 是对 get/set 操作监听
 * mutableStateListOf/mutableStateMapOf 会对 item 的操作进行监听
 */
@Composable  // 有状态组件
private fun WithState130() {
  val content = "Hello hsicen"
  Text(text = content)
}

@Composable  // 无状态组件
private fun WithoutState131(content: String = "Hello hsicen") {
  Text(text = content)
}

// 变量需要用 mutableStateOf 包裹，如果在 compose scope 中，还需要再包上一层 remember。
fun ComponentActivity.withState132() {
  setContent {
    var name by remember { mutableStateOf("hsicen") }
    TextField(value = name, onValueChange = {
      // check input content.
      name = it
      Log.i(MainActivity.TAG, "withState132: $name")
    })
  }
}