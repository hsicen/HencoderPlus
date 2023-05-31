package com.hsicen.a05_modifier

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp

/******====== 39. DrawModifier 的⼯作原理和对绘制的精细影响 ======******/

/**
 * 基本写法：[ModifierDraw01]
 *
 * 工作原理：
 *  1. 在 modifier.set() 内部的遍历过程中，会被塞⼊这个 DrawModifier 右边最近的 LayoutModifier 所属的 ModifiedLayoutNode，
 *     因此它的绘制范围由它最右边的 LayoutModifier 决定；
 *  2. ⼀个 Modifier 链条上的多个 DrawModifier ，会以「左边优先于右边」的顺序进⾏调⽤，所以「左边的」总是包含「右边的」；
 *  3. DrawModifier 不会主动绘制内部（右边）的 DrawModifier ，
 *     但 drawContent() 函数可以进⾏这项⼯作，所以需要主动调⽤这个函数来确保绘制的逻辑链条不断开。
 */
fun ComponentActivity.composeModifier05() {
  setContent {
    ModifierDraw08()
  }
}

@Composable
private fun ModifierDraw01() {
  Modifier.drawWithContent {
    // 这部分内容 被内部盖住....
    drawContent()
    // 这部分内容 盖住内部....
  }

  // 本质上等价于
  Modifier.then(object : DrawModifier {
    override fun ContentDrawScope.draw() {
      // 这部分内容 被内部盖住....
      drawContent()
      // 这部分内容 盖住内部....
    }
  })
}

@Composable
private fun ModifierDraw02() {
  Box(
    modifier = Modifier
      .requiredSize(80.dp)
      .background(Color.Yellow)
      .requiredSize(40.dp)
  )

  // result: 40.dp
}

@Composable
private fun ModifierDraw03() {
  Box(
    modifier = Modifier
      .requiredSize(80.dp)
      .background(Color.Yellow)
      .size(40.dp)
  )

  // result: 80.dp
}

@Composable
private fun ModifierDraw04() {
  Box(
    modifier = Modifier
      .size(80.dp)
      .background(Color.Yellow)
      .requiredSize(40.dp)
  )

  // result: 40.dp
}

@Composable
private fun ModifierDraw05() {
  Box(
    modifier = Modifier
      .size(80.dp)
      .background(Color.Yellow)
      .size(40.dp)
      .drawWithContent {
        val canvas = drawContext.canvas
        val paint = Paint().apply {
          color = Color.Black
          strokeWidth = 10.dp.toPx()
        }
        drawContent()
        canvas.drawLine(Offset(0f, 0f), Offset(80f.dp.toPx(), 80f.dp.toPx()), paint)
      }
  )

  // result: 80.dp
}

@Composable
private fun ModifierDraw06() {
  Box(
    modifier = Modifier
      .size(80.dp)
      .background(Color.Yellow)
      .drawWithContent { }

    // result: 80.dp
  )
}

@Composable
private fun ModifierDraw07() {
  Box(
    modifier = Modifier
      .size(80.dp)
      .drawWithContent { }
      .background(Color.Yellow)

    // result: empty
  )
}

@Composable
private fun ModifierDraw08() {
  Box(
    modifier = Modifier
      .size(80.dp)
      .drawWithContent() {
        val canvas = drawContext.canvas.nativeCanvas
        val paint = android.graphics
          .Paint()
          .apply {
            color = android.graphics.Color.BLACK
            strokeWidth = 20f
            style = android.graphics.Paint.Style.FILL
            textSize = 50f
          }

        drawContent()
        canvas.drawText("hsicen", 20f, 40f, paint)
      }
      .background(Color.Gray)

    // result: 80.dp
  )
}