package com.hsicen.a02_sample

import android.os.Bundle
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
 */
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      Column(Modifier.padding(16.dp)) {
        Image(
          rememberAsyncImagePainter("https://cdn.pixabay.com/photo/2015/05/28/18/50/the-three-gorges-788314__340.jpg"),
          "Coil",
          modifier = Modifier.wrapContentSize()
        )
        Text(text = "Hello", fontSize = 40.sp, color = Color.Green)
        Text(text = "Hello World", fontSize = 20.sp, color = Color.Red)

        val names = listOf("Java", "Kotlin", "Flutter", "Swift", "Kotlin", "Flutter", "Swift", "Kotlin", "Flutter", "Swift", "Kotlin", "Flutter", "Swift")
        LazyColumn {
          items(names) { item ->
            Text(text = item)
            Spacer(modifier = Modifier.size(1.dp))
          }

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

                }
            )
            Spacer(modifier = Modifier.size(8.dp))
          }

          items(names) { item ->
            Text(
              text = item,
              Modifier
                .background(Color.Green)
                .padding(8.dp)
                .background(Color.Magenta)
                .padding(8.dp)
            )
            Spacer(modifier = Modifier.size(1.dp))
          }

          item {
            Button(onClick = { }) {
              Text(text = "智力 +10")
            }
            Spacer(modifier = Modifier.size(8.dp))

            OutlinedButton(onClick = {}) {
              Text(text = "力量 +10")
            }

            TextButton(onClick = {}) {
              Text(text = "攻击 +10")
            }

            FloatingActionButton(onClick = {}) {
              Text(text = "智力 +100")
            }
          }
        }
      }
    }
  }
}