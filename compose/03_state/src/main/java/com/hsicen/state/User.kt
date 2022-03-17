package com.hsicen.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


// 可靠类型
data class User(val name: String, val age: Int)

// 不可靠类型
class User1(var name: String)

@Stable // 识别为可靠类型
class User2(var name: String)

// 常用写法，当 name 改变时，在使用到 name 的地方，会通知 composition
class User3(name: String) {
  var name by mutableStateOf(name) // 识别为可靠类型
}

//
class User4(name: String, company: Company) {
  var name by mutableStateOf(name)
  var company by mutableStateOf(company)
}

class Company(address: String) {
  var address by mutableStateOf(address)
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