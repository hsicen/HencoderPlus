package com.hsicen.hellocompose.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hsicen.hellocompose.WeViewModel
import com.hsicen.hellocompose.ui.theme.WeComposeTheme

/**
 * @author: hsicen
 * @date: 2022/4/27 14:11
 * @email: codinghuang@163.com
 * description: Office 365 Api 测试
 *
 * 应用名称：Graph Android Quickstart
 * 应用 ID：62906a86-4ebb-4fb2-b975-eb49ad25aeb5
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


  }
}