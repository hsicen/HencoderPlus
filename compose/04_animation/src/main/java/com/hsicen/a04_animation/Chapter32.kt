package com.hsicen.a04_animation

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/******====== 32. Crossfade ======******/
/**
 * 作用：⽤动画的⽅式让内部组件的交替（出现和消失）以淡⼊淡出的⽅式呈现
 * 用法：把组件的外层套上 Crossfade
 */
fun ComponentActivity.composeAnimation24() {

  setContent {
    CrossFade01()
  }
}

@Composable
private fun CrossFade01() {
  var big by remember { mutableStateOf(false) }

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    Crossfade(big, label = "Crossfade", animationSpec = tween(2000)) {
      /*when(it){
        1 -> {}
        2 -> {}
        else -> {}
      }*/

      if (it) {
        Box(
          modifier = Modifier
            .size(96.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Green)
        )
      } else {
        Box(
          modifier = Modifier
            .size(96.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Red)
        )
      }
    }

    Button(
      onClick = {
        big = !big
      }, modifier = Modifier.padding(top = 16.dp)
    ) {
      Text(text = "Change")
    }
  }
}
