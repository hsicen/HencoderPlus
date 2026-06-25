package com.hsicen.a02_sample

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/******====== 1.6 自定义 Composable ======******/

/**
 * 1.需要控制布局，不能因为外部因素而改变
 * 2.在传统View的等价写法 => 自定义View ？
 *                        => XML布局文件 ？ XML没有逻辑处理
 *                        => 自定义View + XML 布局文件
 * 3.对 布局、绘制、触摸 等逻辑进行封装（onDraw、onMeasure、onLayout、onTouchEvent、onInterceptTouchEvent）
 */

@Composable
fun ComponentActivity.StateScreen061() {
    val choices = remember { listOf("名称（A-Z）", "最新安装", "最占空间", "不常用") }
    val selectItem = remember { mutableIntStateOf(0) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                "你当前选择了：${choices[selectItem.intValue]}",
                fontSize = 24.sp, color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            Surface(
                modifier = Modifier
                    .width(210.dp)
                    .padding(top = 8.dp),
                shadowElevation = 2.dp
            ) {
                Column {
                    choices.forEachIndexed { index, choice ->
                        Text(
                            choice, fontSize = 18.sp,
                            color = if (index == selectItem.intValue) Color.Red else Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, bottom = 8.dp)
                                .clickable { selectItem.intValue = index }
                        )
                    }
                }
            }
        }
    }

}


