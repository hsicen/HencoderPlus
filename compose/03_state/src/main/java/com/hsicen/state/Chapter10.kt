package com.hsicen.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlin.reflect.KProperty

/******====== 10.自定义 Composable ======******/
/**
 * 自定义Composable
 *  1. compose 编译器插件 (compiler plugin)，直接干预编译过程，对函数进行修改 (可以跨平台)
 *  2. 面向切面编程 (AOP): AnnotationProcessor/修改字节码 (只能用于JVM)
 *  3. Compose编译器插件 @Composable -> 识别符
 *  4. 自定义Composable = 自定义View/Xml布局文件 ?
 *     xml 是标记语言，没有逻辑能力
 *     等价物 -> xml布局文件 + 自定义View
 */
@Composable
fun MainScreen() {
  Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()
  ) {
    Text("Hello World.")
  }
}

// 使用 by 代理
class NameDelegate {
  // 获取值
  operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
    return "$thisRef, thank you for delegating '${property.name}' to me!"
  }

  // 设置值
  operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
    println("$value has been assigned to '${property.name}' in $thisRef")
  }
}