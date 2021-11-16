package com.hsicen.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

  @Composable
  fun TabItem(@DrawableRes iconId: Int, title: String, tint: Color, modifier: Modifier = Modifier) {
    Column(
      modifier.padding(vertical = 8.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Icon(painterResource(iconId), title, Modifier.size(24.dp), tint)
      Text(text = title, fontSize = 11.sp, color = tint)
    }
  }


  @Composable
  fun WeBottomBar(select: Int) {
    Row {
      TabItem(selectIcon(0, select), "聊天", selectColor(0 == select), Modifier.weight(1f))
      TabItem(selectIcon(1, select), "通讯录", selectColor(1 == select), Modifier.weight(1f))
      TabItem(selectIcon(2, select), "发现", selectColor(2 == select), Modifier.weight(1f))
      TabItem(selectIcon(3, select), "我", selectColor(3 == select), Modifier.weight(1f))
    }
  }

  private fun selectIcon(pos: Int, select: Int): Int {
    return when (pos) {
      0 -> if (0 == select) R.drawable.ic_chat_filled else R.drawable.ic_chat_outlined
      1 -> if (1 == select) R.drawable.ic_contacts_filled else R.drawable.ic_contacts_outlined
      2 -> if (2 == select) R.drawable.ic_discover_filled else R.drawable.ic_discover_outlined
      else -> if (3 == select) R.drawable.ic_me_filled else R.drawable.ic_me_outlined
    }
  }

  private fun selectColor(select: Boolean) = if (select) Color.Green else Color.Black

  @Preview(showBackground = true)
  @Composable
  fun PreviewTabItem() {
    TabItem(selectIcon(2, 0), "聊天", selectColor(false))
  }

  @Preview(showBackground = true)
  @Composable
  fun PreviewBottomBar() {
    WeBottomBar(1)
  }
}