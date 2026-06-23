package com.hsicen.a02_sample

import androidx.activity.ComponentActivity
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

/******====== 1.3 MutableState 和 mutableStateOf 自动更新 ======******/

/**
 * 自动更新：
 *  1. List -> MutableList   =>  State -> MutableState
 *
 *  by 和 = 的区别：
 *
 */
@Composable
fun ComponentActivity.StateScreen031() {
    // 当前作用域在 name 改变后会触发重组，代码块会一直被执行

    val name = mutableStateOf("hsicen") // name 是 State 类型
    var nameStr = name // nameStr 是 State 类型

    val color by animateColorAsState(Color.Green)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(name.value, color = color, fontSize = TextUnit(30f, TextUnitType.Sp))
    }

    LaunchedEffect(Unit) {
        lifecycleScope.launch {
            delay(3000.milliseconds)
            name.value = "miky"
        }
    }
}

@Composable
fun ComponentActivity.StateScreen032() {
    // 当前作用域在 name 改变后会触发重组，代码块会一直被执行

    var name by mutableStateOf("hsicen") // name 是 State 类型
    var nameStr = name // nameStr 是 String 类型

    val color by animateColorAsState(Color.Green)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(name, color = color, fontSize = TextUnit(30f, TextUnitType.Sp))
    }

    LaunchedEffect(Unit) {
        lifecycleScope.launch {
            delay(3000.milliseconds)
            name = "miky"
        }
    }
}

@Composable
fun ComponentActivity.StateScreen033() {
    // 使用 remember 当前作用域不会触发重组

    var name by remember { mutableStateOf("hsicen") }
    val color by animateColorAsState(Color.Green)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(name, color = color, fontSize = TextUnit(30f, TextUnitType.Sp))
    }

    LaunchedEffect(Unit) {
        lifecycleScope.launch {
            delay(3000.milliseconds)
            name = "miky"
        }
    }
}