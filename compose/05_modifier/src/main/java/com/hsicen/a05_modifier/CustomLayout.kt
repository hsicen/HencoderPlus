package com.hsicen.a05_modifier

import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density
import com.hsicen.extension.extensions.dp2px

// 1.使用 Layout() 组件创建自定义布局
@Composable
fun CustomLayout(
  modifier: Modifier = Modifier,
  content: @Composable CustomLayoutScope.() -> Unit
) {
  Layout({ CustomLayoutScopeInstance.content() }, modifier) { measurables, constraints ->
    measurables.forEach {
      //2.拿到 parentData,进行自定义处理
      val data = it.parentData as? String
    }

    layout(80.dp2px, 80.dp2px) {

    }
  }
}

@LayoutScopeMarker
@Immutable
interface CustomLayoutScope { // 设置只有在当前 Scope 中才能使用以下定义的函数
  // 3.提供设置 ParentData 的函数接口
  @Stable
  fun Modifier.stringData(str: String): Modifier

  @Stable
  fun Modifier.weightData(weight: Float): Modifier

  @Stable
  fun Modifier.bigData(big: Boolean): Modifier
}

private object CustomLayoutScopeInstance : CustomLayoutScope {
  @Stable
  override fun Modifier.stringData(str: String) = this.then(object : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any {
      return str
    }
  })

  @Stable
  override fun Modifier.weightData(weight: Float) = this.then(object : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any {
      return weight
    }
  })

  @Stable
  override fun Modifier.bigData(big: Boolean) = this.then(object : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any {
      return big
    }
  })
}