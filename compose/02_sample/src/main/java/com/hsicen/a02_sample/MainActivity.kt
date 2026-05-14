package com.hsicen.a02_sample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
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
 *  3.Compose 可以和原生 View 交互,没有绕开原生，Flutter 直接使用NDK和底层渲染引擎交互(skia)，绕开了原生
 *
 * Compose组件
 *  1. Text() -> drawText/drawTextRun
 *  2. Image() -> canvas.drawBitmap/drawColor, Icon()
 *  3. Column(), Row() -> LinearLayout
 *  4. LazyColumn(), LazyRow() -> RecyclerView
 *  5. Box() -> FrameLayout
 *  6. ConstraintLayout(), MotionLayout()
 *  7. Pager() -> ViewPager
 *  8. Button()
 *  9. Modifier.verticalScroll() -> ScrollView
 *
 * Modifier(属性控制)
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
 *  0.Compile (compose 编译插件)
 *  1.Runtime (State, Remember ...)
 *  2.Ui (draw, measure, layout, touch)
 *  3.Animation
 *  4.Foundation (Box, Row, Column, LazyColumn, Image)
 *  5.Material (Text, Icon, Button, FloatActionButton)
 *
 * 同一个组下多个包依赖关系:
 *  ui-tooling: include ui, ui-tooling-preview
 *  material: include material and material-icons-core
 *  foundation: include foundation-layout
 *
 *  use compose including: material, material-icons-extended, ui-tooling.
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
            .clip(RoundedCornerShape(12.dp))
            .clickable {
              Toast
                .makeText(this@MainActivity, "Top image.", Toast.LENGTH_SHORT)
                .show()
            }
        )
        Text(text = "Hello World", fontSize = 40.sp, color = Color.Green)
        Text(text = "Hello World", fontSize = 20.sp, color = Color.Red)

        val names = listOf(
          "1-Java", "Kotlin", "Flutter", "Swift", "ReactNative",
          "2-Java", "Kotlin", "Flutter", "Swift", "ReactNative",
          "3-Java", "Kotlin", "Flutter", "Swift", "ReactNative",
          "4-Java", "Kotlin", "Flutter", "Swift", "ReactNative"
        )

        // 列表数据
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
          // 列表集合
          items(names) { item ->
            Text(text = item)
            Spacer(modifier = Modifier.size(1.dp))
          }

          // 单个 item
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
            )

            Spacer(modifier = Modifier.size(8.dp))
          }

          // padding 处理 (都是内边距padding, 没有外边距margin)，通过添加 padding 的顺序来实现 margin 效果
          items(names) { item ->
            Text(
              text = item,
              Modifier
                .background(Color.Green)
                .padding(8.dp)
                .background(Color.Gray, RoundedCornerShape(6.dp))
                .padding(8.dp)
                .background(Color.Yellow)
                .clickable {
                  Toast
                    .makeText(this@MainActivity, item, Toast.LENGTH_SHORT)
                    .show()
                }
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