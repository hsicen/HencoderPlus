package com.hsicen.viewbinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * 作者：hsicen  5/23/21 22:25
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：ViewBinding基类封装
 */
abstract class BindingFragment<T : ViewBinding> : Fragment() {
  private var _binding: T? = null
  protected val binding get() = _binding!!

  @Suppress("UNCHECKED_CAST")
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    val type = javaClass.genericSuperclass
    val clazz = (type as ParameterizedType).actualTypeArguments[0] as Class<T>
    val method = clazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
    _binding = method.invoke(null, layoutInflater, container, false) as T

    this.viewLifecycleOwner.lifecycle.addObserver(object : LifecycleEventObserver {
      override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) _binding = null
      }
    })
    return binding.root
  }
}