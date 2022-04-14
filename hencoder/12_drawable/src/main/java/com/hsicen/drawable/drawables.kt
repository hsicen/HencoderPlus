package com.hsicen.drawable

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable


fun Context.bitmapDrawable(resId: Int): BitmapDrawable {
  val bitmap = BitmapFactory.decodeResource(resources, resId)
  return BitmapDrawable(resources, bitmap).apply {
    setAntiAlias(true)
  }
}

