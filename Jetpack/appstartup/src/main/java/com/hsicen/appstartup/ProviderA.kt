package com.hsicen.appstartup

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log

/**
 * 作者：hsicen  5/31/21 15:38
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class ProviderA : ContentProvider() {
  override fun onCreate(): Boolean {

    context?.applicationContext?.let {
      Log.d("hsc", "ProviderA")
    }

    return true
  }

  override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
    return null
  }

  override fun getType(uri: Uri): String? {
    return null
  }

  override fun insert(uri: Uri, values: ContentValues?): Uri? {
    return null
  }

  override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
    return 0
  }

  override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
    return 0
  }

}