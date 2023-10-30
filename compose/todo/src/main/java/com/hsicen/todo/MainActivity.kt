package com.hsicen.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.hsicen.todo.data.DataCenter
import com.hsicen.todo.ui.Todos
import com.hsicen.todo.ui.TodosContent
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

    WindowCompat.setDecorFitsSystemWindows(window, false)
    setContentView(ComposeView(this).apply {
      consumeWindowInsets = true
      setContent {
        TodoTheme {
          TodosContent(DataCenter.exampleTodoState)
        }
      }
    })
  }

  @Preview(showBackground = true)
  @Composable
  fun MainScreenPreview() {
    TodosContent(DataCenter.exampleTodoState)
  }

  @Preview(showBackground = true)
  @Composable
  fun TodoPreview() {
    TodoTheme {
      Todos(DataCenter.todos, LazyListState())
    }
  }
}
