package com.hsicen.a02_sample

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

/******====== 1.5 View传统组件在 Compose 的等价物 ======******/

/**
 * ComponentActivity.setContent()
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
 */

@Composable
fun ComponentActivity.StateScreen051() {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(32.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var name by remember { mutableStateOf("hsicen") }
        Text(
            name,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.background(Color.Yellow)
        )

        Image(
            rememberAsyncImagePainter("https://cdn.pixabay.com/photo/2015/05/28/18/50/the-three-gorges-788314__340.jpg"),
            "Coil",
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 8.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    Toast.makeText(this@StateScreen051, "Top image.", Toast.LENGTH_SHORT).show()
                })

        LazyColumn {
            item {
                Text(
                    "开始计数",
                    fontSize = 25.sp,
                    modifier = Modifier
                        .padding(12.dp)
                        .background(Color.Green)
                )
            }

            items(30) { pos ->
                Text(
                    "Current count: $pos",
                    modifier = Modifier.padding(6.dp)
                )
            }

            item {
                Text(
                    "结束计数.",
                    fontSize = 25.sp,
                    modifier = Modifier
                        .padding(12.dp)
                        .background(Color.Red)
                )
            }
        }
    }
}


