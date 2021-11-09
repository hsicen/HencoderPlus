package com.hsicen.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hsicen.hellocompose.ui.theme.HelloComposeTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      HelloComposeTheme {

      }
    }
  }
}

@Composable
private fun TabItem(@DrawableRes iconId: Int, title: String, tint: Color) {
  Column(Modifier.padding(16.dp)) {
    Icon(painterResource(iconId), title, Modifier.size(24.dp), tint)
    Text(text = title, fontSize = 11.sp, color = tint)
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewTabItem() {
  Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
    TabItem(iconId = R.drawable.ic_chat_filled, title = "聊天", tint = Color.Green)
    TabItem(iconId = R.drawable.ic_discover_filled, title = "朋友圈", tint = Color.Gray)
  }
}