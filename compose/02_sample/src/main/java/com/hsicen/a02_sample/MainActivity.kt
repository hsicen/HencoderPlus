package com.hsicen.a02_sample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

/**
 * 作者：hsicen  12/13/21 22:20
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：compose sample.
 *
 * 声明式UI
 *  1.关键在于只需要声明，而不是界面看起来像声明.
 *  2.或者说不需要命令式更新
 *
 * Compose独立于平台
 *  1.意思是不依赖于Android, 好处是可以独立更新，以及加入多平台支持 (包括更完美的预览功能)
 *  2.Compose的多平台和Flutter的跨平台，关键不在于名字，而在于初始定位不同，Flutter初始定位在跨平台，Compose初始定位在推广Kotlin
 *
 * Compose组件
 *  1. Text(), Image(), Icon()
 *  2. Column(), Row(), LazyColumn(), LazyRow()
 *  3. Box(), ConstraintLayout()
 *  4. Pager(), Button()
 *
 * Modifier
 *  1.对顺序敏感
 *  2.多次调用会依次应用，而不是互相覆盖
 *  3.通用属性使用 Modifier，专属属性使用函数
 *  4.padding() 增加边距
 *    background() 设置背景
 *    clip() 切边
 *    width(), height(), size() 设置尺寸
 *    clickable() 设置监听
 *
 * compose 分层:
 *  1.Runtime (State, Remember ...)
 *  2.Ui (draw, measure, layout, touch)
 *  3.Animation
 *  4.Foundation (Row, Column, LazyColumn)
 *  5.Material (Button, FloatActionButton)
 *
 * ui-tooling: include ui, ui-tooling-preview
 * material: include material and material-icon-core
 * use compose including: material, material-icons-extended, ui-tooling.
 *
 * use compose guide:
 *  1. just use material or foundation is enough.
 *  2. need ui-tooling if you want preview function.
 *  3. material-icons-extend also need include alone.
 */
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      // common components.
      Column(Modifier.padding(16.dp)) {
        Image(
          rememberAsyncImagePainter("https://cdn.pixabay.com/photo/2015/05/28/18/50/the-three-gorges-788314__340.jpg"),
          "Coil",
          modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(6.dp))
            .clickable {
              Toast
                .makeText(this@MainActivity, "Top image.", Toast.LENGTH_SHORT)
                .show()
            }
        )
        Text(text = "Hello", fontSize = 40.sp, color = Color.Green)
        Text(text = "Hello World", fontSize = 20.sp, color = Color.Red)

        val names = listOf(
          "Java", "Kotlin", "Flutter", "Swift",
          "Java", "Kotlin", "Flutter", "Swift",
          "Java", "Kotlin", "Flutter", "Swift",
          "Java", "Kotlin", "Flutter", "Swift"
        )
        LazyColumn {
          items(names) { item ->
            Text(text = item)
            Spacer(modifier = Modifier.size(1.dp))
          }

          // shape size 处理 (clip)
          item {
            Spacer(modifier = Modifier.size(8.dp))
            Image(
              rememberAsyncImagePainter("https://cdn.pixabay.com/photo/2015/05/28/18/50/the-three-gorges-788314__340.jpg"),
              "Coil",
              contentScale = ContentScale.FillBounds,
              modifier = Modifier
                .size(width = 320.dp, height = 180.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                  Toast
                    .makeText(this@MainActivity, "Hello world.", Toast.LENGTH_SHORT)
                    .show()
                }
            )
            Spacer(modifier = Modifier.size(8.dp))
          }

          // padding 处理 (都是内padding)
          items(names) { item ->
            Text(
              text = item,
              Modifier
                .background(Color.Green)
                .padding(8.dp)
                .background(Color.Gray)
                .padding(8.dp)
            )
            Spacer(modifier = Modifier.size(1.dp))
          }

          // button
          item {
            Button(onClick = {
              Toast.makeText(this@MainActivity, "button container.", Toast.LENGTH_SHORT).show()
            }) {
              Column {
                Text(text = "智力 +10", modifier = Modifier.clickable {
                  Toast.makeText(this@MainActivity, "button content.", Toast.LENGTH_SHORT).show()
                })
                Text(text = "(GM 特权)", modifier = Modifier.clickable {
                  Toast.makeText(this@MainActivity, "gm right.", Toast.LENGTH_SHORT).show()
                })
              }
            }
            Spacer(modifier = Modifier.size(8.dp))

            OutlinedButton(onClick = {}) {
              Text(text = "力量 +10")
            }

            TextButton(onClick = {}) {
              Text(text = "攻击 +10")
            }

            FloatingActionButton(
              onClick = {},
              modifier = Modifier.padding(8.dp)
            ) {
              Text(text = "智力 +100", modifier = Modifier.padding(16.dp))
            }
          }
        }
      }
    }
  }
}