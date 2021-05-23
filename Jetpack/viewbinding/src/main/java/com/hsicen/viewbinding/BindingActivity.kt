package com.hsicen.viewbinding

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * 作者：hsicen  5/23/21 22:06
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：ViewBinding基类封装
 */
@Suppress("UNCHECKED_CAST")
abstract class BindingActivity<T : ViewBinding> : AppCompatActivity() {
  protected lateinit var binding: T

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val type = javaClass.genericSuperclass
    if (type is ParameterizedType) {
      val clazz = type.actualTypeArguments[0] as Class<T>
      val method = clazz.getMethod("inflate", LayoutInflater::class.java)
      binding = method.invoke(null, layoutInflater) as T
      setContentView(binding.root)
    }
  }
}