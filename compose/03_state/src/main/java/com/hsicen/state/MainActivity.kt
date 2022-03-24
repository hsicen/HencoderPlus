package com.hsicen.state

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
 * 刷新：组合(Composition)、布局、绘制 -> Compose、布局、绘制
 * 组合过程：拼凑出界面实际内容
 * Compose/Composition -> ComposeView -> AndroidComposeView -> LayoutNode
 *
 * 过程：组合 Compose Composition
 * 结果：组合 Composition
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
 *  将 StateRecord 和当前 set 的值比较是否相同
 *  overwritable -> overwritableRecord -> 返回一个可用的 StateRecord
 *    1.snapshotId 相同，直接返回该 StateRecord
 *    2.不相同，newOverwritableRecord() 创建一个
 *  writeObserver 将 readObserver 中记录标记为无效，在下一帧来临时进行刷新
 *
 * StateRecord：变量
 * Snapshot：整个状态；可以对应多个 StateRecord；一个 StateRecord 对应一个 Snapshot
 *  多线程同步，批量更新支持
 *  1.系统有多个 Snapshot 的时候，它们是有先后关系的
 *  2.同一个 StateObject 的每个 StateRecord 都有它们对应的 Snapshot 的 id.
 *    StateRecord 和 Snapshot 就算不直接对应，只要 StateRecord 的 Snapshot 对另一个是有效的，另一个就能读取到这个 StateRecord。
 *
 *
 * 两个订阅过程：
 *  1.对 Snapshot 中读/写 StateObject 对象的订阅，分别订阅读和写，所以有两个接收者：readObserver 和 writeObserver
 *      发生时间：
 *          订阅：Snapshot 创建的时候
 *          通知：读和写的时候
 *  2.对【每一个】StateObject 的应用做订阅
 *      发生时间：
 *          订阅：第一个订阅的 readObserver 被调用（通知）的时候
 *          通知：StateObject 新值被应用的时候 Recompose
 *
 *
 * SnapshotMutableStateImpl.value
 *  get()：记录读 -> 订阅行为
 *  set()：标记失效 -> 刷新行为
 *  「应用」事件：标记失效
 *
 *
 * 重组作用域和remember
 * 运行时拿到某行代码: 反射/ASM -> 不算
 * Recompose Scope: 重组作用域
 *   指的是在 Compose 的 @Composable 代码中，被划归为⼀体的代码块，这些代码块会在变量发⽣变
 *   化从⽽导致界⾯需要重新组合（重组、Recompose）的时候，被⼀起标记为⽆效、并在稍后⼀起执⾏。
 *
 * remember 为缓存作用域，用来在 Composable 函数中声明的变量防止 Recompose 造成变量重复初始化
 * 带参数的 remember, 可以根据 key 是否改变来决定是否使用上次计算的缓存
 *
 *
 * State
 * 状态：UI 组件的属性
 * Stateful 有状态、Stateless ⽆状态：其实是有内部状态、⽆内部状态。
 *
 * 一个有状态的组件 -> 无状态组件  ==> 把这个组件的状态抽出来
 * State Hoisting: 状态提升
 * 状态尽量少暴露，尽量下沉，减少出错
 *
 * TextField() -> belong material, not ui or foundation.
 *
 * 数据：缓存：本地数据 + 网络数据
 * 多数据来源：需要解决的问题->数据同步性
 * 解决：单数据来源
 * Single Source of Truth
 * Jetpack -> ViewModel -> Repository[数据库+网络]
 *
 * Unidirectional Data Flow -> 单向数据流
 * TextField -> BasicTextField
 *
 *
 * mutableStateOf 是对 get/set 操作监听
 * mutableStateListOf/mutableStateMapOf 会对 item 的操作进行监听
 */
class MainActivity : AppCompatActivity() {
  private val hsicen: String by NameDelegate()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    compositionLocal2()
  }


  /**
   * 拼凑出界面实际内容
   */
  private fun stateScreen1() {
    val name = mutableStateOf("hsicen") // MutableState<T>

    setContent {
      Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
      ) {
        Text(name.value)
      }
    }

    lifecycleScope.launch {
      delay(3000)
      name.value = "黄思程~~~"
    }
  }

  // by 代理实现
  private fun stateScreen2() {
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

  /**
   * 重组作用域和remember
   * Recompose Scope: 重组作用域
   */
  private fun stateScreen3() {
    setContent { // Recompose Scope
      var name by mutableStateOf("hsicen")
      Text(
        name, textAlign = TextAlign.Center, fontSize = 24.sp,
        modifier = Modifier.padding(16.dp)
      )

      lifecycleScope.launch {
        delay(3000)
        name = "黄思程~~~"
      }
    }
  }

  private fun stateScreen4() {
    setContent {
      var name by mutableStateOf("hsicen")
      Button(onClick = { }, modifier = Modifier.padding(16.dp)) { // Recompose Scope
        Text(name, textAlign = TextAlign.Center, fontSize = 24.sp)
      }

      lifecycleScope.launch {
        delay(3000)
        name = "黄思程~~~"
      }
    }
  }

  private fun stateScreen5() {
    setContent { // Recompose Scope
      var name by remember { mutableStateOf("hsicen") }
      Text(
        name, textAlign = TextAlign.Center, fontSize = 24.sp,
        modifier = Modifier.padding(16.dp)
      )

      lifecycleScope.launch {
        delay(3000)
        name = "黄思程~~~"
      }
    }
  }

  /**
   * 重复的 str 不会计算长度
   * @param str String
   */
  @Composable
  private fun ShowContent(str: String) {
    val len = remember(str) { str.length }
    Text(text = "content is: $len")
  }

  // 有状态组件
  @Composable
  private fun WithState() {
    val content = "Hello hsicen"
    Text(text = content)
  }

  // 无状态组件
  @Composable
  private fun WithoutState(content: String = "Hello hsicen") {
    Text(text = content)
  }

  @Composable
  private fun WithState2() {
    var name by remember { mutableStateOf("hsicen") }
    TextField(value = name, onValueChange = {
      // check input content.
      name = it
      println("content change  -> $it")
    })
  }

  private fun stateScreen6() {
    var num by mutableStateOf(1)

    setContent {
      Text(text = "The content of num $num", modifier = Modifier.clickable {
        num++
      })
    }
  }

  private fun stateScreen7() {
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
          Text(text = "current num is $num.")
        }
      }
    }
  }

  private fun stateScreen8() {
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

  private fun stateScreen9() {
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

  private fun stateScreen10() {
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


  /****===Recompose Scope===****/
  /**
   * 性能风险
   * Compose: 自动更新 -> 更新范围过大、超过需求 -> 跳过没必要的更新
   *
   * Structure equality 结构性相等  kotlin 的 ==  --- Java 的equals
   * Kotlin 的 ===  Java 的 == Referential Equality
   *
   * 可靠类--本身及其属性不可变 -> 结构性相等判断是否 Recompose
   * 不可靠类--本身及其属性可变 -> 全部 Recompose
   *
   * 先明确问题：可靠性问题，而不是 不跳过 的问题
   * @Stable 注解
   *
   * Object.equals() / Any.equals()
   * [两个相等的 User 在之后变得不相等] 就不会发生
   *
   * @Stable 的稳定
   *  1.现在相等就永远相等
   *  2.当公开属性改变的时候，通知到用到这个属性的 Composition，触发刷新
   *  3.公开属性需要全部是稳定的 可靠属性
   *
   * @Immutable 注解, 含义是内部不会改变, 即比 @State 还要稳定, 但实际上两者的行为完全一致
   *
   * 稳定性实践:
   *  1.不要轻易重写 equals()
   *  2.用 by mutableStateOf() 来代理 Var 修饰的公开属性; 或者加上 @State/@Immutable 注解
   *  3.对于公开属性也全部是可靠类型, 只能靠写代码的时候多注意, 但其实一般来说不需要做任何事
   */
  private fun composeScope() {
    var name by mutableStateOf("hsicen")

    setContent { // Recompose Scope
      println("Recompose scope 范围测试1")
      Column {
        println("Recompose scope 范围测试2")
        Heavy()
        Text(text = name, modifier = Modifier.clickable {
          name = "黄思程~~~"
        })
      }
    }
  }

  private fun composeScope2() {
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
                .makeText(this@MainActivity, "clicked text.", Toast.LENGTH_SHORT)
                .show()
            })
        }
      }
    }
  }

  private fun composeScope3() {
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

  private fun composeScope4() {
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

  private fun composeScope5() {
    val company = Company("四川省成都市天府新区")
    val user = User4("hsicen", company)

    setContent { // Recompose Scope
      println("Recompose scope 范围测试1")
      Column {
        println("Recompose scope 范围测试2")
        HeavyCompany(user)
        Text(text = user.name, modifier = Modifier.clickable {
          // user.name = "黄思程~~~"
          company.address = "四川省成都市武侯区"
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
  private fun HeavyUser(user: User) {
    println("Recompose scope 范围测试: heavy")
    Text(text = "Heavy content: ${user.name}.")
  }

  @Composable
  private fun HeavyCompany(user: User4) {
    println("Recompose scope 范围测试: heavy")
    Text(text = "Heavy content: ${user.company.address}.")
  }


  /****=== deriveStateOf & rememberOf ===****/
  /**
   * convert one or multiple state objects into another state
   *
   * 当改变状态的方式不是用过赋值，而是改变内部状态时，使用 derivedStateOf 才能够监听到状态改变，触发刷新
   * 通过赋值的方式触发状态改变，可以直接使用带参数的 remember
   *
   * 1. 监听状态变化从而实现自动刷新，有两种写法：带参数的 remember(); 不带参数的 remember() + derivedStateOf()
   * 2. 上面的说法不全对, 对于状态对象来说( mutableStateListOf(), mutableStateOf() ), 带参数的 remember() 不能使用, 只能使用 derivedStateOf()
   * 3. 对于函数参数里的字符串(Int 之类)，监听链条会被掐断，所以不能使用 derivedStateOf()，只能使用带参数的 remember()
   *
   * 带参数的 remember(): 可以判断对象的重新赋值，而 derivedStateOf() 不能完美做到，所以带参数的 remember()
   * derivedStateOf(): 适用于监听状态对象
   * by mutableStateOf() 所代理的对象：用两种都行，因为其 状态改变 和 重新赋值 是同一回事
   * 拥有内部状态的类型 (状态对象的类型) 同时又是 Composable 函数的参数，需要同时使用带参数的 remember() 和 derivedStateOf()
   *
   * 函数参数 -> remember()
   * 内部状态 -> derivedStateOf()
   */
  private fun deriveState() {
    setContent {
      var name by remember { mutableStateOf("hsicen") }
      val processName by remember { derivedStateOf { name.uppercase() } }

      Text(text = processName, modifier = Modifier.clickable {
        name = "hello hsicen"
      })
    }
  }

  private fun deriveState1() {
    setContent {
      var name by remember { mutableStateOf("hsicen") }
      val processName = remember(name) { name.uppercase() }

      Text(text = processName, modifier = Modifier.clickable {
        name = "hello hsicen"
      })
    }
  }

  private fun deriveState2() {
    var a1 = "hsicen"
    val a2 = a1
    a1 = "milky"
    println("a1==a2 ${a1 == a2} $a1 $a2")

    val b1 = mutableListOf(1, 2, 3, 4)
    val b2 = b1
    b1.add(5)
    println("b1==b2 ${b1 == b2} $b1 $b2")

    setContent {
      val names = remember { mutableStateListOf("hsicen", "milky") }
      val processNames = remember(names) {
        names.map { it.uppercase() } // names 的结构性相等，是同一个对象，不会触发这行代码
      }

      Text(text = "$processNames", modifier = Modifier.clickable {
        names.add("compose")
      })
    }
  }

  private fun deriveState3() {
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

  private fun deriveState4() {
    @Composable
    fun ProcessName(name: String, onNameTap: () -> Unit) {
      // 可以正常刷新，因为两次 name 的值不一样
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

  private fun deriveState5() {
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

  private fun deriveState6() {
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

  private fun deriveState7() {
    @Composable
    fun ProcessName(name: List<String>, onNameTap: () -> Unit) {
      // 无法刷新， 只有 name 重新赋值才能触发刷新
      // val processName =  remember(name) { name.map { it.uppercase() } }

      // 可以刷新，监听到了 name 的改变，但如果 name 被重新赋值，则无法监听到 name 的改变
      // val processName by  remember { derivedStateOf { name.map { it.uppercase() } } }

      // name 改变或者被重新赋值，都可以被监听到
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


  /****=== CompositionLocal ===****/
  /**
   * State hoisting 状态提升 -> 有内部状态到无内部状态
   * CompositionLocal: composition 的局部变量
   * 具有函数穿透功能的局部变量 ==> 作用相当于函数参数 ==> 加强版的函数参数，不需要显式传递的函数参数
   * 变量 -> 函数参数 -> CompositionLocal
   *
   * 声明成一个不会造成更大影响范围的对象
   */
  private fun compositionLocal() {
    @Composable
    fun User(name: String) {
      Text(text = name)
    }

    setContent {
      val name = "hsicen" // local variable
      User(name = name)
    }
  }

  private val localName = compositionLocalOf<String> { error("name 没有提供值") }
  private fun compositionLocal2() {
    @Composable
    fun User() {
      // 从内部获取有穿透能力的数据
      Text(localName.current)
    }

    setContent {
      // 从外部提供有穿透能力的数据
      val name = "hsicen" // local variable
      CompositionLocalProvider(localName provides name) {
        User()
      }
    }
  }
}