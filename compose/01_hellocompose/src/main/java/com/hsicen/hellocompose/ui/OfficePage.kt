package com.hsicen.hellocompose.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hsicen.hellocompose.WeViewModel
import com.hsicen.hellocompose.ui.theme.WeComposeTheme

/**
 * @author: hsicen
 * @date: 2022/4/27 14:11
 * @email: codinghuang@163.com
 * description: Office 365 Api 测试
 *
 */
@Composable
fun OfficePage(modifier: Modifier = Modifier) {
  val viewModel: WeViewModel = viewModel()
  val offsetX by animateFloatAsState(if (viewModel.officePage) 0f else 1f)

  Column(
    modifier
      .offsetPercent(offsetX)
      .background(WeComposeTheme.colors.background)
      .fillMaxSize()
  ) {

    // 顶部标题栏
    WeTopBar("Office 365 Api 测试") {
      viewModel.endOffice()
    }

    val mUser by remember(viewModel.mUser) { mutableStateOf(viewModel.mUser) }
    Button(modifier = modifier.padding(16.dp), onClick = {
      viewModel.signIn()
    }) {
      Column {
        Text("登录")
        Spacer(modifier.size(16.dp))
        Text("User info: $mUser")
      }
    }

    Button(modifier = modifier.padding(16.dp), onClick = {
      viewModel.signOut()
    }) {
      Column {
        Text("注销")
      }
    }
  }
}