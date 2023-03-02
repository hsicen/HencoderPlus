package com.hsicen.state

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * 10.自定义 Composable
 *
 * 面向切面编程(AOP)：1.Annotation Processor/注解处理器 2.修改字节码AMS
 * Compose 使用的是编译器插件，因为 Compose 是跨平台的，上述AP和AMS只能用在JAVA平台
 *
 * 编译器通过 @Composable 注解来识别这些需要被修改的函数，从而添加相关参数，让其可以被正确调用
 * 所有加了 @Composable 注解的函数只能在 Composable 函数中调用
 *
 * Compose 只是一个 UI 框架，并不是 Kotlin 这个语言的特性，所以不能和协程一样和 Kotlin 绑定
 *
 * xml + 自定义 View = 自定义 Composable
 */


@Composable
fun UserInfo(user: User) {
  Column {
    Text(text = "用户信息:")
    Text(text = "\t姓名: ${user.name}")
    Text(text = "\t年龄: ${user.age}")
  }
}

@Preview(showBackground = true)
@Composable
fun TestUser() {
  UserInfo(user = User("小明", 18))
}
