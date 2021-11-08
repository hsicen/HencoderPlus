package com.hsicen.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
  Column {
    Icon(painterResource(iconId), title, Modifier.size(24.dp), tint)
    Text(text = title, fontSize = 11.sp, color = tint)
  }
}