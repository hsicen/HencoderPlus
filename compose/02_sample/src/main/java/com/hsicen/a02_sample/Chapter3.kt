package com.hsicen.a02_sample

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/******====== 1.3 MutableState 和 mutableStateOf 自动更新 ======******/

/**
 * 自动更新：
 *  1. List -> MutableList   =>  State -> MutableState
 *  2.
 */
@Composable
fun ComponentActivity.stateScreen031() {
    var name by mutableStateOf("hsicen")
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(name)
    }

    lifecycleScope.launch {
        delay(3000)
        name = "miky"
    }
}
