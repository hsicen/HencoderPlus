package com.hsicen.a05_modifier

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

/******====== 42. PointerInputModifier 的功能介绍和原理简析 ======******/

/**
 * PointerInputModifier 的写法: [ModifierInput01]
 *  1. 最简单、最上层的间接调⽤: Modifier.clickable()
 *  2. ⽐ clickable() 功能多⼀些，也是最上层的: Modifier.combinedClickable()
 *  3. Modifier.pointerInput()
 *    基本写法：在⾥⾯直接调⽤⼿势侦测函数 detectTapGestures
 *    ⾃主侦测：forEachGesture/awaitEachGesture + awaitPointerEvent/awaitFirstDown()
 *
 *  PointerInputModifier 原理:
 *  1.和 DrawModifier 在 LayoutNode 的 modifier 遍历过程中⼀样，
 *    都会⽤从左到右的顺序放进固定的链表中，并和右边最近的 LayoutModifier 放在⼀起，
 *    触摸的范围和位置也由右边的 LayoutModifier 决定；
 *  2.和同⼀个 LayoutModifier 保存在⼀起（即它左边）的多个 PointerInputModifier ，
 *    左边的担当右边的「⽗ Modifier 」的⻆⾊。
 */
fun ComponentActivity.composeModifier06() {
  setContent {
    ModifierInput02()
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ModifierInput01() {
  val ctx = LocalContext.current

  Column {
    // 最简单的写法
    Box(modifier = Modifier
      .size(80.dp)
      .background(Color.Red)
      .clickable {
        Toast
          .makeText(ctx, "Input 最基础用法", Toast.LENGTH_SHORT)
          .show()
      })

    // 最基础多功能用法
    Box(modifier = Modifier
      .size(80.dp)
      .background(Color.Yellow)
      .combinedClickable(
        onLongClick = { // 长按回调
          Toast
            .makeText(ctx, "Input 基础 长按", Toast.LENGTH_SHORT)
            .show()
        },
        onDoubleClick = { // 双击回调
          Toast
            .makeText(ctx, "Input 基础 双击 666", Toast.LENGTH_SHORT)
            .show()
        }, onClick = { // 点击回调
          Toast
            .makeText(ctx, "Input 基础 点击", Toast.LENGTH_SHORT)
            .show()
        }
      ))

    // pointerInput 基础使用
    Box(modifier = Modifier
      .size(80.dp)
      .background(Color.Green)
      .pointerInput(Unit) {
        // 内部封装的 awaitEachGesture
        detectTapGestures(onDoubleTap = {
          Toast
            .makeText(ctx, "onDoubleTap", Toast.LENGTH_SHORT)
            .show()
        }, onLongPress = {
          Toast
            .makeText(ctx, "onLongPress", Toast.LENGTH_SHORT)
            .show()
        }, onPress = {
          Toast
            .makeText(ctx, "onPress", Toast.LENGTH_SHORT)
            .show()
        }, onTap = {
          Toast
            .makeText(ctx, "onTap", Toast.LENGTH_SHORT)
            .show()
        })
      })

    // pointerInput 自定义侦测
    Box(modifier = Modifier
      .size(80.dp)
      .background(Color.Gray)
      .pointerInput(Unit) {
        // forEachGesture() 和 awaitPointerEventScope() 并不是规定的写法，但⼏乎只能这么写
        /*forEachGesture {
          awaitPointerEventScope {

          }
        }*/

        // 新 api 的写法， 内部包含 awaitPointerEventScope
        awaitEachGesture {
          Log.d("hsc", "awaitEachGesture")
          // 触摸侦测算法
          val down = awaitFirstDown()
          down.consume()
        }
      })
  }
}


@Composable
private fun ModifierInput02() {
  Box(modifier = Modifier
    .size(80.dp)
    .background(Color.Red)
    .clickable {}
    .size(30.dp)
    .background(Color.Gray)
    .size(200.dp)
    .background(Color.Yellow)
  )
}

@Composable
private fun ModifierInput03() {
  Box(modifier = Modifier
    .size(80.dp)
    .background(Color.Red)
    .clickable {} // 父
    .clickable {} // 父 -> 子
    .clickable {} // 父 -> 子 -> 子
    .size(40.dp))
}
