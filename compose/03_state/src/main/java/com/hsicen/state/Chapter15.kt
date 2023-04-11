package com.hsicen.state

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


/****=== 15.重组的性能风险和智能优化 ===****/
/**
 * 性能风险
 *  Compose: 自动更新 -> 更新范围过大、超过需求 -> 跳过没必要的更新(智能更新)
 *  传统View：手动更新
 *
 * 自动优化：
 *  在 Recompose 过程中，⾃动跳过参数没变化的 Composable 函数的内部代码。
 *  但参数必须全部都是「可靠类型」
 *
 * 可靠类型：可以根据 @Stable 注解的注释的三个要求来定义
 *  「相等」的恒定性；
 *   公开属性的改变⾃动通知到 Composition;
 *   公开属性也全部都是可靠类型。
 *
 * compose 在重组时用的是结构性相等来判断是否需要重组：
 *  结构性相等：kotlin 的 ==  ==> Java 的 equals (Structure equality)
 *  引用性相等：Kotlin 的 === ==> Java 的 == (Referential Equality)
 *
 * 可靠性：
 *  可靠类--本身及其属性不可变 -> 结构性相等判断是否 Recompose
 *  不可靠类--本身及其属性可变 -> 全部 Recompose
 *
 * @Stable 注解 ==> 稳定性标记，只要现在相等，在将来也相等；人为保证，小心谨慎
 * 稳定性的判断：
 *  1.现在相等就永远相等
 *  2.当公开属性改变的时候，通知到用到这个属性的 Composition，触发刷新
 *  3.公开属性需要全部是稳定的/可靠属性
 *  compose 只会判断第二条，来决定是稳定还是不稳定类型
 *  基本类型和 String 天生就是稳定类型
 *
 * @Immutable 注解, 含义是内部不会改变, 即比 @State 还要稳定, 但实际上两者的行为完全一致
 *
 * 稳定性实践:
 *  1.不要轻易重写 equals()，data class 会自动重写 equals()
 *  2.用 by mutableStateOf() 来代理 Var 修饰的公开属性(Compose ⾃动判断类型「可靠」的唯⼀依据);
 *    或者加上 @State/@Immutable 注解(同时别忘了⼿动实现「改变⾃动通知到Composition 的功能」)
 *  3.对于公开属性也全部是可靠类型, 只能靠写代码的时候多注意, 但其实一般来说不需要做任何事
 */

// 无参函数，触发重组时不会执行函数内部代码
fun ComponentActivity.composeScope150() {
  var name by mutableStateOf("hsicen")

  println("Recompose scope 范围测试0")
  setContent { // Recompose Scope
    println("Recompose scope 范围测试1")
    Column {
      println("Recompose scope 范围测试2")
      Heavy() // 函数参数没变化，重组时不会执行函数内部代码
      Text(text = name, modifier = Modifier.clickable {
        name = "黄思程~~~"
      })
    }
  }
}

// 有参函数，触发重组时:
//  1.参数改变，会触发重组
//  2.参数不变，不会重组
fun ComponentActivity.composeScope151() {
  var name by mutableStateOf("hsicen")

  println("Recompose scope 范围测试0")
  setContent { // Recompose Scope
    println("Recompose scope 范围测试1")
    Column {
      println("Recompose scope 范围测试2")
      Heavy(name)
      Heavy("Hello World")
      Text(text = name, modifier = Modifier.clickable {
        name = "黄思程~~~"
      })
    }
  }
}

fun ComponentActivity.composeScope152() {
  var name by mutableStateOf("hsicen")

  setContent {
    println("Recompose scope 范围测试1")
    Column {
      println("Recompose scope 范围测试2")
      Button(onClick = {
        name = "黄思程~~~"
      }) { // Recompose Scope
        println("Recompose scope 范围测试3")
        Heavy()
        Text(text = name, modifier = Modifier
          .background(Color.Cyan)
          .padding(16.dp)
          .clickable {
            Toast
              .makeText(this@composeScope152, "clicked text.", Toast.LENGTH_SHORT)
              .show()
          })
      }
    }
  }
}

// 自定义类引用相等 ==> 不触发重组
fun ComponentActivity.composeScope153() {
  var name by mutableStateOf("hsicen")
  val user = User("hsicen", 18)

  setContent { // Recompose Scope
    println("Recompose scope 范围测试1")
    Column {
      println("Recompose scope 范围测试2")
      HeavyUser(user)
      Text(text = name, modifier = Modifier.clickable {
        name = "黄思程~~~"

      })
    }
  }
}

// 自定义类结构性相等 ==> 不触发重组
fun ComponentActivity.composeScope154() {
  var name by mutableStateOf("hsicen")
  var user = User("hsicen", 18)

  setContent { // Recompose Scope
    println("Recompose scope 范围测试1")
    Column {
      println("Recompose scope 范围测试2")
      HeavyUser(user)
      Text(text = name, modifier = Modifier.clickable {
        name = "黄思程~~~"
        user = User("hsicen", 18)
      })
    }
  }
}

// 自定义类结构性相等，但是为不可靠类(有 var 属性) ==> 触发重组
// 现在相等不代表以后也相等
fun ComponentActivity.composeScope155() {
  var name by mutableStateOf("hsicen")
  var user = User1("hsicen")

  setContent { // Recompose Scope
    println("Recompose scope 范围测试1")
    Column {
      println("Recompose scope 范围测试2")
      HeavyUser(user)
      Text(text = name, modifier = Modifier.clickable {
        name = "黄思程~~~"
        user = User1("hsicen")
      })
    }
  }
}

// 用 @Stable 注解，来告诉编译器 不可靠类不会改变，不要触发重组
// 现在结构性相等(equals)，以后也相等；需要人为保证，容易出错
fun ComponentActivity.composeScope156() {
  var name by mutableStateOf("hsicen")
  var user = User2("hsicen")

  setContent { // Recompose Scope
    println("Recompose scope 范围测试1")
    Column {
      println("Recompose scope 范围测试2")
      HeavyUser(user)
      Text(text = name, modifier = Modifier.clickable {
        name = "黄思程~~~"
        user = User2("hsicen")
      })
    }
  }
}

// 使用 @Stable 注解，来告诉编译器 不可靠类不会改变，不要触发重组
// 没有重写 equals 的不可靠类，只有同一个对象，才会判定为相等
fun ComponentActivity.composeScope157() {
  var name by mutableStateOf("hsicen")
  val user = User3("hsicen")

  setContent { // Recompose Scope
    println("Recompose scope 范围测试1")
    Column {
      println("Recompose scope 范围测试2")
      HeavyUser(user)
      Text(text = name, modifier = Modifier.clickable {
        name = "黄思程~~~"
      })
    }
  }
}

// 当公开属性改变时，没法通知使用到这个属性的地方进行刷新
fun ComponentActivity.composeScope158() {
  var name by mutableStateOf("hsicen")
  val user = User3("hsicen")

  setContent { // Recompose Scope
    println("Recompose scope 范围测试1")
    Column {
      println("Recompose scope 范围测试2")
      HeavyUser(user)
      Text(text = name, modifier = Modifier.clickable {
        name = "黄思程~~~"
        user.name = "黄思程~~~" // 改变公开属性
      })
    }
  }
}

// 使用 mutableStateOf 来包裹公开属性，
// 让使用到公开属性的地方在公开属性改变时进行Recompose
fun ComponentActivity.composeScope159() {
  val company = Company("四川省成都市天府新区")
  val user = User4("hsicen", company)

  setContent { // Recompose Scope
    println("Recompose scope 范围测试1")
    Column {
      println("Recompose scope 范围测试2")
      HeavyCompany(user)
      Text(text = user.name, modifier = Modifier.clickable {
        user.name = "黄思程~~~"
        company.address = "四川省成都市武侯区"
      })
    }
  }
}

// 依然会被识别为可靠类型
fun ComponentActivity.composeScope1591() {
  val cat = Cat("Cat: Tom")
  val user = User6("hsicen", cat)

  setContent { // Recompose Scope
    println("Recompose scope 范围测试1")
    Column {
      println("Recompose scope 范围测试2")
      HeavyCat(user)
      Text(text = user.name, modifier = Modifier.clickable {
        // user.name = "黄思程~~~"
        cat.name = "Cat: Jerry" // 不会触发刷新
      })
    }
  }
}

@Composable
private fun Heavy() {
  println("Recompose scope 范围测试: heavy")
  Text(text = "Heavy content.")
}

@Composable
private fun Heavy(content: String) {
  println("Recompose scope 范围测试: heavy")
  Text(text = "Heavy $content.")
}

@Composable
private fun HeavyUser(user: User) {
  println("Recompose scope 范围测试: heavy")
  Text(text = "Heavy content: ${user.name}.")
}

@Composable
private fun HeavyUser(user: User1) {
  println("Recompose scope 范围测试: heavy")
  Text(text = "Heavy content: ${user.name}.")
}

@Composable
private fun HeavyUser(user: User2) {
  println("Recompose scope 范围测试: heavy")
  Text(text = "Heavy content: ${user.name}.")
}

@Composable
private fun HeavyUser(user: User3) {
  println("Recompose scope 范围测试: heavy")
  Text(text = "Heavy content: ${user.name}.")
}

@Composable
private fun HeavyCompany(user: User4) {
  println("Recompose scope 范围测试: heavy")
  Text(text = "Heavy content: ${user.company.address}.")
}

@Composable
private fun HeavyCat(user: User6) {
  println("Recompose scope 范围测试: heavy")
  Text(text = "Heavy content: ${user.cat.name}.")
}