package com.hsicen.datastore

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

// 顶层函数 全局有效
val Context.spStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreModel(private val app: Application) : AndroidViewModel(app) {
  companion object {
    // key define
    val COUNT = intPreferencesKey("counter")
  }


  // 读取内容
  val count: Flow<Int> = app.spStore.data
    .map { sp ->
      sp[COUNT] ?: 0
    }

  // 写入内容
  fun changeCount() = viewModelScope.launch(Dispatchers.IO) {
    incrementCount()
  }

  private suspend fun incrementCount() {
    app.spStore.edit { sp ->
      val count = sp[COUNT] ?: 0
      sp[COUNT] = count + 1
    }
  }
}
