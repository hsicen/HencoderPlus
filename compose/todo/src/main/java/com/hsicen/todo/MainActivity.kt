package com.hsicen.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.hsicen.todo.data.DataCenter
import com.hsicen.todo.data.Todo
import com.hsicen.todo.ui.theme.TodoTheme

/**
 * 作者：hsicen  11/8/21 21:42
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：A to do list project
 */
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Turn off the decor fitting system windows, which allows us to handle insets
    WindowCompat.setDecorFitsSystemWindows(window, false)
    setContentView(ComposeView(this).apply {
      consumeWindowInsets = true
      setContent {
        TodoTheme {
          MainScreen()
        }
      }
    })
  }

  @Composable
  private fun MainScreen() {
    var inputting by remember { mutableStateOf(false) }
    val animatedFabScale by animateFloatAsState(if (inputting) 0f else 1f, label = "")
    val animatedInputScale by animateFloatAsState(if (inputting) 1f else 0f, label = "")
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(floatingActionButton = {
      FloatingActionButton(onClick = {
        inputting = true
        keyboardController?.show()
      }, modifier = Modifier.scale(animatedFabScale)) {
        Icon(Icons.Filled.Add, "添加")
      }
    }) { pad ->
      println(pad)
      Column(
        modifier = Modifier
          .fillMaxSize()
          .background(MaterialTheme.colorScheme.background)
      ) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
        Box(Modifier.fillMaxSize()) {
          Todos(DataCenter.todos)
          Row(
            Modifier
              .align(Alignment.BottomCenter)
              .fillMaxWidth()
              .padding(16.dp)
              .scale(animatedInputScale)
          ) {
            TextField(value = "", onValueChange = {}, modifier = Modifier.weight(1f))
            IconButton(onClick = {
              inputting = false
              keyboardController?.hide()
            }) {
              Icon(Icons.Filled.Send, "完成")
            }
          }
        }
      }
    }
  }

  @Composable
  private fun Todos(todos: List<Todo>) {
    Surface(
      shape = RoundedCornerShape(8.dp),
      shadowElevation = 8.dp,
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      LazyColumn {
        items(todos) { todo ->
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
              .padding(8.dp)
              .clickable {
                todo.completed = !todo.completed
              }
          ) {
            Checkbox(checked = todo.completed, onCheckedChange = {
              todo.completed = it
            })

            Text(
              text = todo.name,
              style = if (todo.completed) LocalTextStyle.current.copy(textDecoration = TextDecoration.LineThrough)
              else LocalTextStyle.current,
              modifier = Modifier.padding(start = 8.dp)
            )
          }
        }
      }
    }
  }

  @Preview(showBackground = true)
  @Composable
  fun MainScreenPreview() {
    MainScreen()
  }

  @Preview(showBackground = true)
  @Composable
  fun TodoPreview() {
    TodoTheme {
      Todos(DataCenter.todos)
    }
  }
}