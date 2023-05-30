package com.hsicen.a05_modifier

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp

/******====== 37. LayoutModifier 和 Modifier.layout() ======******/

/**
 * LayoutModifier：
 *   1. 作用：修饰 Composable 函数，来达到对尺⼨和位置进⾏修改的⽬的
 *   2. 原理：在 LayoutNode 的测量过程中插⼊ ModifiedLayoutNode，
 *           来达到拦截 Composable 组件预设的测量代码的效果，从⽽实现对组件的测量和布局的⼲预
 *
 * Modifier.layout():
 *   1. 用法：a.调⽤ measurable.measure() 来测量实际的 Composable 组件（或者内层的另⼀个 LayoutModifier ）;
 *           b.⽤返回的 Placeable 对象来计算出实际尺⼨，调⽤ layout() 来吧尺⼨填⼊;
 *           c.同样⽤这个返回的 Placeable 的尺⼨，结合上 constraints 以及⾃身的特性，
 *             计算出偏移量，在 layout() 的 Lambda 参数内部调⽤ Placeable.place<Relative>() 来摆放.
 *   2. 当多个 Modifier.layout() （或其他 LayoutModifier 的函数）连续调⽤时，
 *      会是⼀种「左边测量右边，右边再测量更右边，直到最内部的 Composable 组件」的测量顺序，并且测量之后也会从右往左陆续返回结果。
 *      所以，效果上也会是⼀种「右边总受左边控制」的效果
 */

private val LocalActivity = staticCompositionLocalOf<ComponentActivity> { error("没有初始化") }
fun ComponentActivity.composeModifier04() {

  setContent {
    CompositionLocalProvider(LocalActivity provides this) {
      ModifierLayout()
    }
  }
}

@Composable
private fun ModifierLayout() {
  Box(Modifier.background(Color.Yellow)) {
    Text(
      "hsicen",
      Modifier
        .background(Color.Green)
        .padding(2.dp)
        // 修改被修饰组件的尺寸和位置偏移  装饰效果，无法干涉内部的测量和布局
        .layout { measurable, constraints ->
          val padGap = 10.dp.roundToPx()
          val padGap2 = 2 * padGap

          val placeable = measurable.measure(
            constraints.copy(
              maxWidth = constraints.maxWidth - padGap2,
              maxHeight = constraints.maxHeight - padGap2
            )
          )

          layout(placeable.width + padGap2, placeable.height + padGap2) { // 尺寸
            // 位置偏移
            placeable.placeRelative(padGap, padGap)
            //placeable.placeRelative(placeable.width, placeable.height)
          }
        })
  }
}
