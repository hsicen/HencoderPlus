package com.hsicen.state

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

/****=== 16.deriveStateOf & rememberOf ===****/
/**
 * deriveStateOf: convert one or multiple state objects into another state
 * 当改变状态的方式不是通过赋值，而是改变内部状态时，使用 derivedStateOf 才能够监听到状态改变，触发刷新
 * 通过赋值的方式触发状态改变，可以直接使用带参数的 remember
 *
 * 1. 监听状态变化从而实现自动刷新，有两种写法：带参数的 remember(); 不带参数的 remember() + derivedStateOf()
 * 2. 上面的说法不全对, 对于状态对象来说( mutableStateListOf(), mutableStateOf() ),
 *    带参数的 remember() 不能使用(参数对象没有变，引用传递), 只能使用 derivedStateOf()
 * 3. 对于函数参数里的字符串(Int 之类)，监听链条会被掐断(直接赋值，不是引用传递)，
 *    所以不能使用 derivedStateOf()，只能使用带参数的 remember()
 *
 * 带参数的 remember(): 可以判断对象的重新赋值，而 derivedStateOf() 不能完美做到，所以带参数的 remember() 适合的场景是函数参数
 * derivedStateOf(): 适用于监听状态对象，mutableStateOf() 和 mutableStateListOf()
 * by mutableStateOf() 所代理的对象：用两种都行，因为其 状态改变 和 重新赋值 是同一回事
 * 拥有内部状态的类型 (状态对象的类型) 同时又是 Composable 函数的参数：需要同时使用带参数的 remember() 和 derivedStateOf()
 *
 * 函数参数 -> remember()
 * 内部状态 -> derivedStateOf()
 * 函数参数 + 内部状态 -> remember(参数) + derivedStateOf()
 */

// remember + derivedStateOf：基本类型数据源  ==> 正常运行
fun ComponentActivity.deriveState1601() {
  setContent {
    var name by remember { mutableStateOf("黄翊安") }
    val processName by remember { derivedStateOf { name.uppercase() } }

    Text(text = processName, modifier = Modifier.clickable {
      name = "黄柚柚" // 修改后触发重组
    })
  }
}

// 带参数的 remember：基本类型数据源  ==> 正常运行
fun ComponentActivity.deriveState1602() {
  setContent {
    var name by remember { mutableStateOf("hsicen") }
    val processName = remember(name) { name.uppercase() }

    Text(text = processName, modifier = Modifier.clickable {
      name = "hello hsicen"
    })
  }
}

// 带参数的 remember；list 数据源 ==> 不起作用
fun ComponentActivity.deriveState1603() {
  // 值 copy
  var a1 = "hsicen"
  val a2 = a1
  a1 = "milky"
  println("a1==a2 ${a1 == a2} $a1 $a2")

  // 引用类型
  val b1 = mutableListOf(1, 2, 3, 4)
  val b2 = b1
  b1.add(5)
  println("b1==b2 ${b1 == b2} $b1 $b2")

  setContent {
    val names = remember { mutableStateListOf("hsicen", "milky") }
    val processNames = remember(names) { // 触发了 Recompose，但是没有进入大括号中代码
      names.map { it.uppercase() } // names 的结构性相等(是同一个对象)，不会触发这行代码
    }

    Text(text = "$processNames", modifier = Modifier.clickable {
      names.add("compose")
    })
  }
}

// remember + derivedStateOf；list 数据源 ==> 正常运行
fun ComponentActivity.deriveState1604() {
  setContent {
    val names = remember { mutableStateListOf("hsicen", "milky") }
    val processNames by remember(names) {
      derivedStateOf { names.map { it.uppercase() } }
    }

    Text(text = "$processNames", modifier = Modifier.clickable {
      names.add("compose")
    })
  }
}

// 带参数的 remember：函数参数(基本类型数据源) ==> 正常运行
fun ComponentActivity.deriveState1605() {
  @Composable
  fun ProcessName(name: String, onNameTap: () -> Unit) {
    // 可以正常刷新，因为两次 name 的值不一样
    val processName = remember(name) { name.uppercase() }

    Text(text = processName, modifier = Modifier.clickable {
      onNameTap()
    })
  }

  setContent {
    var name by remember { mutableStateOf("黄柚柚 hyy") }
    ProcessName(name) { // 传进去的是 name 值，而不是一个 StateObject 对象
      name = "黄肉肉 hrr"
    }
  }
}

// remember + derivedStateOf：函数参数(基本类型数据源) ==> 无法运行
fun ComponentActivity.deriveState1606() {
  @Composable
  fun ProcessName(name: String, onNameTap: () -> Unit) {
    // 不能正常刷新，因为这是一个无参数的 remember ，并且传入进来的 name 只是一个值，derivedStateOf 也无法监听到
    val processName by remember { derivedStateOf { name.uppercase() } }



    Text(text = processName, modifier = Modifier.clickable {
      onNameTap()
    })
  }

  setContent {
    var name by remember { mutableStateOf("hsicen") }
    ProcessName(name) { // 传进去的是 name 值，而不是一个 StateObject 对象
      name = "hello hsicen"
    }
  }
}

// 带参数的 remember：函数参数(基本类型数据源) ==> 正常运行
fun ComponentActivity.deriveState1607() {
  @Composable
  fun ProcessName(name: String, onNameTap: () -> Unit) {
    val processName = remember(name) { name.uppercase() }

    Text(text = processName, modifier = Modifier.clickable {
      onNameTap()
    })
  }

  setContent {
    var name by remember { mutableStateOf("hsicen") }
    ProcessName(name) { // 传进去的是 name 值，而不是一个 StateObject 对象
      name = "hello hsicen"
    }
  }
}

// remember + derivedStateOf：函数参数(State 类型的基本类型数据源) ==> 正常运行
fun ComponentActivity.deriveState1608() {
  @Composable
  fun ProcessName(name: State<String>, onNameTap: () -> Unit) {
    // 能正常刷新，虽然这是一个无参数的 remember,但传进来的 name 是一个 StateObject，derivedStateOf 可以监听到
    val processName by remember { derivedStateOf { name.value.uppercase() } }

    Text(text = processName, modifier = Modifier.clickable {
      onNameTap()
    })
  }

  setContent {
    val name = remember { mutableStateOf("hsicen") }
    ProcessName(name) { // 传进去的是 name 是一个 StateObject 对象
      name.value = "hello hsicen"
    }
  }
}

// 函数参数类型 + list 数据源
fun ComponentActivity.deriveState1609() {
  @Composable
  fun ProcessName(name: List<String>, onNameTap: () -> Unit) {
    // 无法刷新， 只有 name 重新赋值才能触发刷新
    // val processName =  remember(name) { name.map { it.uppercase() } }

    // 可以刷新，监听到了 name 的改变，但如果 name 被重新赋值，则无法监听到 name 的改变
    // val processName by  remember { derivedStateOf { name.map { it.uppercase() } } }

    // name 内部改变或者被重新赋值，都可以被监听到
    val processName by remember(name) {
      derivedStateOf {
        name.map { it.uppercase() }
      }
    }

    Text(text = "$processName", modifier = Modifier.clickable {
      onNameTap()
    })
  }

  setContent {
    val names = remember { mutableStateListOf("hsicen", "miky") }
    ProcessName(names) {
      names.add("hello hsicen")
    }
  }
}
