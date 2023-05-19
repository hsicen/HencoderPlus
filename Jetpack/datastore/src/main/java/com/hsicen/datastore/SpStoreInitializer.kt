package com.hsicen.datastore

import android.app.Application
import android.content.Context
import androidx.startup.Initializer

/**
 * @author: hsicen
 * @date: 2023/5/19 14:53
 * @email: codinghuang@163.com
 * description: 自动初始化工作
 */
class SpStoreInitializer : Initializer<Unit> {

  override fun create(context: Context) {
    // App 实例注入
    IDataStoreOwner.app = context as Application
  }

  override fun dependencies() = emptyList<Class<Initializer<*>>>()
}