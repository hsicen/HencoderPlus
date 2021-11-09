package com.hsicen.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    setContent {
      TodoTheme {
        MainScreen()
      }
    }
  }


  @Composable
  private fun MainScreen() {
    var inputting by remember { mutableStateOf(false) }
    val animatedFabScale by animateFloatAsState(if (inputting) 0f else 1f)
    val animatedInputScale by animateFloatAsState(if (inputting) 1f else 0f)

    Scaffold(floatingActionButton = {
      FloatingActionButton(onClick = {
        // 弹出输入框
        inputting = true
      }, modifier = Modifier.scale(animatedFabScale)) {
        Icon(Icons.Filled.Add, "添加")
      }
    }, topBar = {
      TopAppBar {
        Icon(Icons.Filled.Check, "检查")
      }
    }) {
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
          }) {
            Icon(Icons.Filled.Send, "完成")
          }
        }
      }
    }
  }

  @Composable
  private fun Todos(todos: List<Todo>) {
    Surface(
      shape = RoundedCornerShape(8.dp),
      elevation = 8.dp,
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      LazyColumn {
        items(todos) { todo ->
          Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            Checkbox(checked = todo.completed, onCheckedChange = {
              todo.completed = it
            })
            Text(text = todo.name, modifier = Modifier.padding(start = 8.dp))
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