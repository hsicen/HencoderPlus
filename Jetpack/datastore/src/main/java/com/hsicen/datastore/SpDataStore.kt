package com.hsicen.datastore

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

/**
 * Preferences DataStore 封装
 *
 * 使用：
 * ```code
 *  class SettingRepository : BaseRepository(), IDataStoreOwner by DataStoreOwner("Setting"){
 *    // code here
 *  }
 *  ```
 * 使用 startup 自动注入 app
 * ```code
 *  class SpStoreInitializer : Initializer<Unit> {
 *
 *   override fun create(context: Context) {
 *     // App 实例注入
 *     IDataStoreOwner.app = context as Application
 *   }
 *
 *   override fun dependencies() = emptyList<Class<Initializer<*>>>()
 * }
 * ```
 * 注册 Provider
 * ```code
 * <provider
 *   android:name="androidx.startup.InitializationProvider"
 *   android:authorities="${applicationId}.androidx-startup"
 *   android:exported="false"
 *   tools:node="merge">
 *   <meta-data
 *     android:name="com.hsicen.datastore.SpStoreInitializer"
 *     android:value="androidx.startup" />
 * </provider>
 * ```
 */

/******====== 自定义 DataStoreOwner ======******/
interface IDataStoreOwner {
  val context: Context get() = app
  val dataStore: DataStore<Preferences>

  companion object {
    internal lateinit var app: Application
  }
}

open class DataStoreOwner(name: String) : IDataStoreOwner {
  private val Context.dataStore by preferencesDataStore(name)
  override val dataStore get() = context.dataStore
}


/******====== 实现属性委托 ======******/

