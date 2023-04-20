package com.hsicen.a04_animation

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FloatSpringSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/******====== 26. AnimationSpec - FloatAnimationSpec ======******/
fun ComponentActivity.composeAnimation13() {
  var big by mutableStateOf(false)

  setContent {
    val animSize = remember { Animatable(100f) }

    Box(
      contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
      Box(
        modifier = Modifier
          .size(animSize.value.dp)
          .background(Color.Green)
          .clickable {
            big = !big
          })

      LaunchedEffect(key1 = big, block = {
        // animSize.animateTo(if (big) 300f else 100f, FloatTweenSpec())
        animSize.animateTo(if (big) 300f else 100f, FloatSpringSpec())
      })
    }
  }
}

/******====== 1.10 VectorizedAnimationSpec ======******/
