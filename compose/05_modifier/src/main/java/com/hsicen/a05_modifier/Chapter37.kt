package com.hsicen.a05_modifier

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp

/******====== 37/38. LayoutModifier 和 Modifier.layout() ======******/

/**
 * LayoutModifier：
 *   1. 作用：修饰 Composable 函数，来达到对尺⼨和位置进⾏修改的⽬的
 *   2. 原理：在 LayoutNode 的测量过程中插⼊ ModifiedLayoutNode，
 *           来达到拦截 Composable 组件预设的测量代码的效果，从⽽实现对组件的测量和布局的⼲预
 *
 * Modifier.layout():
 *   1. 用法：a.调⽤ measurable.measure() 来测量实际的 Composable 组件（或者内层的另⼀个 LayoutModifier ）;
 *           b.⽤返回的 Placeable 对象来计算出实际尺⼨，调⽤ layout() 来把尺⼨填⼊;
 *           c.同样⽤这个返回的 Placeable 的尺⼨，结合上 constraints 以及⾃身的特性，
 *             计算出偏移量，在 layout() 的 Lambda 参数内部调⽤ Placeable.place<Relative>() 来摆放.
 *   2. 当多个 Modifier.layout() （或其他 LayoutModifier 的函数）连续调⽤时，
 *      会是⼀种「左边测量右边，右边再测量更右边，直到最内部的 Composable 组件」的测量顺序，并且测量之后也会从右往左陆续返回结果。
 *      所以，效果上也会是⼀种「右边总受左边控制」的效果
 *
 * 测量流程(remeasure)：
 *  LayoutNode.remeasure() ->
 *    LayoutNode.measurePassDelegate.remeasure(constraints) ->
 *    LayoutNodeLayoutDelegate.MeasurePassDelegate.performMeasure(constraints) ->
 *    LayoutNodeLayoutDelegate.outerCoordinator.measure(constraints)
 *
 *  outerCoordinator = LayoutNodeLayoutDelegate.layoutNode.nodes.outerCoordinator
 *  measurePassDelegate = layoutDelegate.measurePassDelegate
 *  layoutDelegate : LayoutNodeLayoutDelegate(LayoutNode)
 *
 *  LayoutNode.nodes = NodeChain(LayoutNode) ->
 *    outerCoordinator = innerCoordinator ->
 *    innerCoordinator = InnerNodeCoordinator(layoutNode) ->
 *    measureResultIn = InnerNodeCoordinator.measure()
 *
 *  Text("hsicen", Modifier.padding(10.dp).padding(10.dp))
 *  [LayoutModifier-10.dp -> ModifiedLayoutNode
 *      [LayoutModifier-20.dp -> ModifiedLayoutNode
 *          实际组件 Text() innerCoordinator-InnerNodeCoordinator
 *      ]
 *  ]
 */

private val LocalActivity = staticCompositionLocalOf<ComponentActivity> { error("没有初始化") }
fun ComponentActivity.composeModifier04() {

  setContent {
    CompositionLocalProvider(LocalActivity provides this) {
      ModifierLayout05()
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

@Composable
private fun ModifierLayout01() {
  Box(
    modifier = Modifier
      .padding(150.dp)
      .size(200.dp)
      .background(Color.Green)
  )
}

@Composable
private fun ModifierLayout02() {
  Box(
    modifier = Modifier
      .size(200.dp)
      .background(Color.Green)
      .padding(90.dp)
      .background(Color.Yellow)
  )
}

@Composable
private fun ModifierLayout03() {
  Box(
    modifier = Modifier
      .size(90.dp)
      .background(Color.Green)
      .size(200.dp)
      .background(Color.Yellow)
  ) {
    Text(text = "hsicen")
  }
}

@Composable
private fun ModifierLayout04() {
  Box(
    modifier = Modifier
      .size(200.dp)
      .background(Color.Green)
      .requiredSize(90.dp)
      .background(Color.Yellow)
  ) {
    Text(text = "hsicen")
  }
}

@Composable
private fun ModifierLayout05() {
  Box(
    modifier = Modifier
      .requiredSize(90.dp)
      .background(Color.Green)
      .size(200.dp)
      .background(Color.Yellow)
  ) {
    Text(text = "hsicen")
  }
}
