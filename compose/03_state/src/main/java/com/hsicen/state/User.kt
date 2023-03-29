package com.hsicen.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


// 可靠类型 ==> 判断结构性相等，来决定是否重组
data class User(val name: String, val age: Int)

// 不可靠类型 ==> 直接进行重组
data class User1(var name: String)

@Stable // 识别为可靠类型 重写 equals，结构性相等
data class User2(var name: String)

@Stable // 识别为可靠类型 不重写 equals，用原始的 equals 函数
class User3(var name: String)

class User4(name: String, company: Company) {
  var name by mutableStateOf(name) // 识别为可靠类型
  var company by mutableStateOf(company) // 识别为可靠类型
}

// 常用写法，当 name 改变时，在使用到 name 的地方，会通知 Recomposition
class User5(name: String) {
  var name by mutableStateOf(name) // 识别为可靠类型
}

class Company(address: String) {
  var address by mutableStateOf(address) // 识别为可靠类型
}

// 不可靠示例，但是 compose 会把它识别为一个可靠类型
class Cat(var name: String)
class User6(name: String, cat: Cat) {
  var name by mutableStateOf(name)
  var cat by mutableStateOf(cat)
}

fun main() {

  val user1 = User("hsicen", 18)
  val user2 = User("hsicen", 18)
  val user3 = user1

  println("user1==user2 -> ${user1 == user2}")
  println("user1===user2 -> ${user1 === user2}")

  println("user1==user3 -> ${user1 == user3}")
  println("user1===user3 -> ${user1 === user3}")
}
