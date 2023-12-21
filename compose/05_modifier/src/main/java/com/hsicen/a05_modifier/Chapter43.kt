package com.hsicen.a05_modifier

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp

/******====== 43/44/45. ParentDataModifier 的作用/写法/原理 ======******/

/**
 * 作用：⽤于给⼦组件附加⼀些属性，让⽗组件能在测量和布局的过程中可以拿到，⽤来辅助测量和布局。
 *
 * 写法[CustomLayout]：
 *  1.前提：⾸先是要⽤ Layout() 组件去写⼀个⾃定义布局，才会⽤得到它；
 *  2.⾃定义布局的测量和布局的算法⾥，⽤ Measurable.parentData 拿到开发者给⼦组件设置的属性，然后去计算；
 *  3.写⼀个⾃定义的 Modifier 函数，让它的内部创建⼀个 ParentDataModifier ，
 *    并且实现它的 modifyParentData() 函数，在⾥⾯提供对应的 parentData 。
 *    (1).modifierParentData() 函数的参数，是下⼀个 ParentDataModifier 所提供的数据，可以和⾃⼰的数据进⾏融合后提供。
 *    (2).可以把 Modifier 函数写进专⻔的接⼝或 object 对象，来避免 API 污染的问题。
 *
 * 原理：
 *  存储结构上，和 DrawModifier 以及 PointerInputModifier 完全⼀致；
 *  在执⾏阶段，也是按照「从左到右」的顺序递归调⽤同⼀个 Composable 组件的所有 ParentDataModifier ，
 *  来确保每⼀个都会起作⽤。
 *
 *  Modifier.then(ParentDataModifier1).then(ParentDataModifier2).then(LayoutModifier1)
 *          .then(ParentDataModifier3).then(ParentDataModifier4).then(LayoutModifier2)
 *          .then(ParentDataModifier5).then(ParentDataModifier6)
 *  ==>
 *  ModifiedLayoutNode(
 *    LayoutModifier1[
 *      ...
 *      ParentDataModifier1 -> ParentDataModifier2
 *      ...
 *    ],
 *    ModifiedLayoutNode(
 *      LayoutModifier2[
 *        ...
 *        ParentDataModifier3 -> ParentDataModifier4
 *        ...
 *      ],
 *      InnerPlaceable(
 *        [
 *          ...
 *          ParentDataModifier5 -> ParentDataModifier6
 *          ...
 *        ]
 *      )
 *    )
 *  )
 *
 *
 *  新老 API 类名对应：
 *    LayoutNodeWrapper -> NodeCoordinator
 */
fun ComponentActivity.composeModifier07() {
  setContent {
    ModifierParentData02()
  }
}

@Composable
private fun ModifierParentData01() {
  Row {
    Box(
      modifier = Modifier
        .size(40.dp)
        .background(Color.Green)
        .layoutId("TAG-Small")
        .layoutId("TAG-Big")
        .weight(1f)
    )

    Box(
      modifier = Modifier
        .size(40.dp)
        .background(Color.Red)
        .weight(2f)
    )

    Box(
      modifier = Modifier
        .size(40.dp)
        .background(Color.Blue)
        .weight(1f)
    )
  }
}

@Composable
private fun ModifierParentData02() {
  // 4.使用
  CustomLayout {
    Text(
      "1", modifier = Modifier
        .stringData("1")
        .weightData(1f)
        .bigData(true)
    )

    Text("2", modifier = Modifier.stringData("2"))
    Text("3")

    Box(modifier = Modifier.bigData(false)) {
      Text("4" /*modifier = Modifier.stringData("3")*/)
    }
  }
}