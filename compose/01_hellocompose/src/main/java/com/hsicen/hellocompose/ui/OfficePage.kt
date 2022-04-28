package com.hsicen.hellocompose.ui

import android.app.Activity
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
fun OfficePage(act: Activity, modifier: Modifier = Modifier) {
  val viewModel: WeViewModel = viewModel()
  val offsetX by animateFloatAsState(if (viewModel.officePage) 0f else 1f)
  var refresh by remember { mutableStateOf(1) }

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

    LazyColumn {
      item {
        val mUser by remember(refresh) { mutableStateOf(viewModel.mUser) }
        Button(modifier = modifier.padding(16.dp), onClick = {
          viewModel.signIn(act)
          refresh += 1
        }) {
          Column {
            Text("登录")
            Spacer(modifier.size(16.dp))
            Text("User info:")
            Text("name: ${mUser?.displayName}")
            Text("mail: ${mUser?.mail}")
            Text("zone: ${mUser?.mailboxSettings?.timeZone}")
          }
        }
      }

      item {
        Button(modifier = modifier.padding(16.dp), onClick = {
          viewModel.signOut()
          refresh += 1
        }) {
          Column {
            Text("注销")
          }
        }
      }

      item {
        val mList by remember(refresh) { mutableStateOf(viewModel.mEventList) }
        Button(modifier = modifier.padding(16.dp), onClick = {
          viewModel.queryEvent()
          refresh += 1
        }) {
          Column {
            Text("查询日程")
            Text("日程信息条数: ${mList.size}条")
          }
        }
      }

      items(viewModel.mEventList) { event ->
        Log.d("hsc", "刷新日程列表 $refresh")
        Text("标题：${event.subject}")
        Text("内容：${event.body}")
        Text("开始时间：${event.start}")
        Text("结束时间：${event.end}")
        Text("参与人：${event.attendees}")

        Spacer(modifier.size(4.dp))
      }

      item {
        Button(modifier = modifier.padding(16.dp), onClick = {
          viewModel.createEvent()
          refresh += 1
        }) {
          Column {
            Text("创建日程")
          }
        }
      }
    }
  }
}