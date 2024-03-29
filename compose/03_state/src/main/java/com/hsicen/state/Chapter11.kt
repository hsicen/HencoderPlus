package com.hsicen.state

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/******====== 11.MutableState 和 mutableStateOf() ======******/
/**
 * 刷新：组合(Composition)、布局、绘制 -> Compose、布局、绘制
 *  组合过程：拼凑出界面实际内容(执行Compose代码过程)
 *  Compose/Composition -> ComposeView -> AndroidComposeView -> LayoutNode
 *
 *  过程：组合 Compose/Composition
 *  结果：组合 Composition
 *
 * MutableState -> StateObject -> StateRecord -> Compose  支持事务功能
 * 同一个变量存多个值，支持取消更新
 * 链表保存(StateRecord), StateObject.firstStateRecord 为头结点, StateRecord.next 保存下一个结点
 *
 *
 * SnapshotMutableStateImpl.next(StateStateRecord)  为链表的头结点
 * next.readable()/get函数 拿到链表头
 *  readObserver 记录变量在哪些地方被读了
 *  readable三个参数版本：遍历 StateRecord 链表，找到一个最新的、可用的 StateRecord
 *  将 StateRecord 记录并返回给外部调用者
 *
 * Recompose 重组
 * next.overwritable()/set函数
 *  withCurrent -> current -> readable 遍历 StateRecord 链表，找到一个最新的、可用的 StateRecord
 *  将获取到的 StateRecord 和当前 set 的值比较是否相同
 *  overwritable -> overwritableRecord -> 返回一个可用的 StateRecord
 *    1.snapshotId 相同，直接返回该 StateRecord
 *    2.不相同，newOverwritableRecord() 创建一个
 *  notifyWrite: writeObserver 将 readObserver 中记录标记为无效，在下一帧来临时进行刷新(Recompose)
 *
 * StateRecord：变量
 * Snapshot：整个状态的快照；可以对应多个 StateRecord；但一个 StateRecord 对应一个 Snapshot
 *  多线程同步，批量更新支持
 *  1.系统有多个 Snapshot 的时候，它们是有先后关系的
 *  2.同一个 StateObject 的每个 StateRecord 都有它们对应的 Snapshot 的 id.
 *    StateRecord 和 Snapshot 就算不直接对应，但只要 StateRecord 的 Snapshot 对另一个(Snapshot)是有效的，
 *    则另一个(Snapshot)就能读取到这个 StateRecord.
 *
 * 两个订阅过程：
 *  1.对 Snapshot 中读/写 StateObject 对象的订阅，分别订阅读和写，所以有两个接收者：readObserver 和 writeObserver
 *      发生时间：
 *          订阅：Snapshot 创建的时候
 *          通知：读和写的时候
 *  2.对【每一个】StateObject 的应用做订阅
 *      发生时间：
 *          订阅：第一个订阅的 readObserver 被调用（通知）的时候
 *          通知：StateObject 新值被应用的时候(全局可见)/Recompose
 *
 * SnapshotMutableStateImpl.value
 *  get()：记录读 -> 订阅行为
 *  set()：标记失效 -> 刷新行为
 *  「应用」事件：标记失效
 */

/**** 拼凑出界面实际内容 */
fun ComponentActivity.stateScreen110() {
  val name = mutableStateOf("hsicen") // MutableState<T>

  setContent {
    Box(
      contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
      Text(name.value, fontSize = 50.sp)
    }
  }

  lifecycleScope.launch {
    delay(3000)
    name.value = "黄思程~~~"
  }
}

fun ComponentActivity.stateScreen111() {
  val test = mutableIntStateOf(1)

  setContent {
    Box(
      contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
      Log.d(MainActivity.TAG, "stateScreen11 before: $test")
      Text("${test.intValue}", fontSize = 30.sp)
      test.intValue += 1 // test 值的改变会导致 setContent Scope 一直被重组
      Log.d(MainActivity.TAG, "stateScreen11 after: $test")
    }
  }
}

// by 代理实现
fun ComponentActivity.stateScreen112() {
  var name by mutableStateOf("hsicen")

  setContent {
    Button(modifier = Modifier.fillMaxSize(), onClick = {}) {
      Text(name, textAlign = TextAlign.Center, fontSize = 24.sp)
    }
  }

  lifecycleScope.launch {
    delay(3000)
    name = "黄思程~~~"
  }
}