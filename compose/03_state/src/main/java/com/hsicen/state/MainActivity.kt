package com.hsicen.state

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty

/**
 * 作者：hsicen  12/20/21 22:19
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：State
 *
 * Composable
 *  1. compose 编译器插件 (compiler plugin)，直接干预编译过程，对函数进行修改 (可以跨平台)
 *  2. 面向切面编程 (AOP): AnnotationProcessor/修改字节码 (只能用于JVM)
 *  3. Compose编译器插件 @Composable -> 识别符
 *  4. 自定义Composable = 自定义View/Xml布局文件
 *
 *
 * 刷新：组合(Composition)、布局、绘制 -> Compose、布局、绘制
 * 组合过程：拼凑出界面实际内容
 * Compose/Composition -> ComposeView -> AndroidComposeView -> LayoutNode
 *
 * 过程：组合 Compose Composition
 * 结果：组合 Composition
 *
 * MutableState -> StateObject -> StateRecord -> Compose 支持事务功能
 * 同一个变量存多个值，支持取消更新
 * 链表保存( StateRecord ) firstStateRecord(next) 为头结点
 *
 * Recompose 重组
 *
 * next.readable()/get函数  三个参数版本：遍历 StateRecord 链表，找到一个最新的、可用的 StateRecord
 * next.overwritable()/set函数
 * MutableState 的 value 被 get 时，使用 Snapshot.readObserver 进行记录
 *  set 时，使用 Snapshot.writeObserver 进行记录
 *
 * StateRecord：变量
 * Snapshot：整个状态；可以对应多个 StateRecord；一个 StateRecord 对应一个 Snapshot
 *
 * 1.系统有多个 Snapshot 的时候，它们是有先后关系的
 * 2.同一个 StateObject 的每个 StateRecord 都有它们对应的 Snapshot 的 id.
 *   StateRecord 和 Snapshot 就算不直接对应，只要 StateRecord 的 Snapshot 对另一个是有效的，另一个就能读取到这个 StateRecord。
 *
 * 两个订阅过程：
 *  1.对 Snapshot 中读/写 StateObject 对象的订阅，分别订阅读和写，所以有两个接收者：readObserver 和 writeObserver
 *      发生时间：
 *          订阅：Snapshot 创建的时候
 *          通知：读和写的时候
 *  2.对【每一个】StateObject 的应用做订阅
 *      发生时间：
 *          订阅：第一个订阅的 readObserver 被调用（通知）的时候
 *          通知：StateObject 新值被应用的时候
 *
 * SnapshotMutableStateImpl.value
 *  get()：记录读 -> 订阅行为
 *  set()：标记失效 -> 刷新行为
 *  「应用」事件：标记失效
 *
 *
 *  重组作用域和remember
 *
 *  运行时拿到某行代码：【反射 -> 字节码】不算  不可行
 *
 *  Recompose Scope: 重组作用域
 *
 */
class MainActivity : AppCompatActivity() {
  private val hsicen: String by NameDelegate()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      var name by mutableStateOf("hsicen")
      Button(modifier = Modifier.fillMaxSize(), onClick = {}) {
        Text(name, textAlign = TextAlign.Center, fontSize = 24.sp)
      }

      lifecycleScope.launch {
        delay(3000)
        name = "黄思程~~~"
      }
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
}