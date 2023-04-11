package com.hsicen.state

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


/******====== 14.更新 List 不会触发更新? -- 状态机制的背后 ======******/
/**
 * 如果需要一个变量在 compose 中的读写被自动更新，直接使用 mutableStateOf 包裹这个变量的声明即可；
 * 如果这个变量是在 compose scope 中声明的，则还需要包上一层 remember，避免 recompose 时被重复初始化。
 *
 * 变量的自动更新是因为 mutableStateXXXOf 对其所包裹对象的 get()/set() 添加了监听
 *
 * mutableStateOf     ==> 基本类型
 * mutableStateListOf ==> list
 * mutableStateMapOf  ==> map
 *
 * 自定义类型 ？？？
 */
fun ComponentActivity.stateScreen140() {
  var num by mutableStateOf(1)

  setContent {
    Text(text = "The content of num $num", modifier = Modifier.clickable {
      num++
    })
  }
}

// 对 list 进行 add 等相关操作不会更新，因为没有对 list 的操作进行读写监听
fun ComponentActivity.stateScreen141() {
  val nums by mutableStateOf(mutableListOf(1, 2, 3, 4, 5))

  setContent {
    Column {
      Text(text = "add list data", modifier = Modifier
        .padding(16.dp)
        .clickable {
          // nums 的 add 操作没有进行 读/写 监听操作
          nums.add(nums.last() + 1)
        })

      for (num in nums) {
        Text(
          text = "current num is $num.",
          modifier = Modifier.padding(16.dp)
        )
      }
    }
  }
}

// 对 list 对象本身的操作(get/set)添加了 读/写 监听操作
fun ComponentActivity.stateScreen142() {
  var nums by mutableStateOf(mutableListOf(1, 2, 3, 4, 5))

  setContent {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(text = "add list data", modifier = Modifier
        .padding(16.dp)
        .clickable {
          // nums 的 set/get 操作进行了 读/写 监听操作
          nums = (nums + (nums.last() + 1)).toMutableList()
        })

      for (num in nums) {
        Text(text = "current num is $num.")
      }
    }
  }
}

// 通过其它变量的改变触发 recompose 来触发 list 的刷新
fun ComponentActivity.stateScreen143() {
  val nums by mutableStateOf(mutableListOf(1, 2, 3, 4, 5))
  var flag by mutableStateOf("点击我刷新数据")

  setContent {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(text = flag, modifier = Modifier
        .padding(16.dp)
        .clickable {
          flag = "$flag."
        })

      Text(text = "add list data", modifier = Modifier
        .padding(16.dp)
        .clickable {
          nums.add(nums.last() + 1)
        })

      for (num in nums) {
        Text(text = "current num is $num.")
      }
    }
  }
}

// 使用系统提供的 mutableStateListOf 类型来自动更新 list 数据类型
fun ComponentActivity.stateScreen144() {
  // nums 的 add 操作进行了 读/写 监听操作
  val nums = mutableStateListOf(1, 2, 3, 4, 5)

  setContent {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(text = "add list data", modifier = Modifier
        .padding(16.dp)
        .clickable {
          nums.add(nums.last() + 1)
        })

      for (num in nums) {
        Text(text = "current num is $num.")
      }
    }
  }
}