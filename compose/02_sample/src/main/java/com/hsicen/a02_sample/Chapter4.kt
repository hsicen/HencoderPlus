package com.hsicen.a02_sample

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

/******====== 1.4 组合、重组作用域 和 remember() ======******/

/**
 * Composition
 * Layout
 * Draw
 *
 * Recompose
 *
 * LayoutNode
 * NodeCoordinator
 *
 *
 * Composition：实现界面的更新工作
 */


// StateScreen041 和 Box 作用域的重组，name 会重新初始化
@Composable
fun ComponentActivity.StateScreen041() {
    // name值改变，导致整个作用域被重组了 recompose
    // 包括 name 的初始化也被重组了
    // 1,2,3,4 -> 1,2
    var name by mutableStateOf("hsicen")

    Log.d("StateScreen041", "StateScreen041 作用域1")
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
        Text(name, fontSize = 50f.sp)
        Log.d("StateScreen041", "BOX 作用域2")

        LaunchedEffect(Unit) {
            Log.d("StateScreen041", "LaunchedEffect 作用域3")
            lifecycleScope.launch {
                Log.d("StateScreen041", "lifecycleScope 作用域4")
                delay(3000.milliseconds)
                name = "miky"
            }
        }
    }
}

// StateScreen041 和 Box 作用域的重组，name 会重新初始化
@Composable
fun ComponentActivity.StateScreen042() {
    Log.d("StateScreen042", "StateScreen042 作用域1")
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
        // name值改变，导致整个作用域被重组了 recompose
        // 包括 name 的初始化也被重组了
        // 1,2,3,4 -> 1,2  Box是 inline 函数
        var name by mutableStateOf("hsicen")

        Text(name, fontSize = 50f.sp)
        Log.d("StateScreen042", "BOX 作用域2")

        LaunchedEffect(Unit) {
            Log.d("StateScreen042", "LaunchedEffect 作用域3")
            lifecycleScope.launch {
                Log.d("StateScreen042", "lifecycleScope 作用域4")
                delay(3000.milliseconds)
                name = "miky"
            }
        }
    }
}

@Composable
fun ComponentActivity.StateScreen043() {
    // 1,2,3,4 -> 1,2  Box是 inline 函数
    // 由于 remember，name 不会重新初始化

    var name by remember { mutableStateOf("hsicen") }
    Log.d("StateScreen043", "StateScreen043 作用域1")
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
        Text(name, fontSize = 50f.sp)
        Log.d("StateScreen043", "BOX 作用域2")

        LaunchedEffect(Unit) {
            Log.d("StateScreen043", "LaunchedEffect 作用域3")
            lifecycleScope.launch {
                Log.d("StateScreen043", "lifecycleScope 作用域4")
                delay(3000.milliseconds)
                name = "miky"
            }
        }
    }
}


@Composable
fun ComponentActivity.StateScreen044() {
    // 1,2,3,4 -> 1,2  Box是 inline 函数

    Log.d("StateScreen044", "StateScreen044 作用域1")
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
        // 由于 remember，name 不会重新初始化
        var name by remember { mutableStateOf("hsicen") }
        Text(name, fontSize = 50f.sp)
        Log.d("StateScreen044", "BOX 作用域2")

        LaunchedEffect(Unit) {
            Log.d("StateScreen044", "LaunchedEffect 作用域3")
            lifecycleScope.launch {
                Log.d("StateScreen044", "lifecycleScope 作用域4")
                delay(3000.milliseconds)
                name = "miky"
            }
        }
    }
}


@Composable
fun ComponentActivity.StateScreen045() {
    // 把 Box 内容抽成独立的可重启 Composable，让它形成自己的重组作用域
    // name 改变时，只重组 NameContent（作用域2），不会重组 StateScreen045（作用域1）
    // 1,2,3,4 -> 2  作用域1 不再被触发

    Log.d("StateScreen045", "StateScreen045 作用域1")
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
        // 调用具名 @Composable 函数 → 编译器为其生成独立的 restart group
        NameContent()
    }
}

@Composable
private fun ComponentActivity.NameContent() {
    // 独立的重组作用域（作用域2）：name 变化只会让这里重组
    // 配合 remember，name 不会被重新初始化
    var name by remember { mutableStateOf("hsicen") }
    Log.d("StateScreen045", "NameContent 作用域2")

    Text(name, fontSize = 50f.sp)

    LaunchedEffect(Unit) {
        Log.d("StateScreen045", "LaunchedEffect 作用域3")
        lifecycleScope.launch {
            Log.d("StateScreen045", "lifecycleScope 作用域4")
            delay(3000.milliseconds)
            name = "miky"
        }
    }
}

