package com.hsicen.viewbinding

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * 作者：hsicen  5/23/21 18:12
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：ViewBinding 扩展
 */

//Activity
inline fun <reified T : ViewBinding> Activity.inflate() = lazy {
  inflateViewBinding<T>(layoutInflater).apply { setContentView(root) }
}

//Dialog
inline fun <reified T : ViewBinding> Dialog.inflate() = lazy {
  inflateViewBinding<T>(layoutInflater).apply { setContentView(root) }
}

//通过反射获取到Binding类
inline fun <reified T : ViewBinding> inflateViewBinding(layoutInflater: LayoutInflater) =
  T::class.java.getMethod("inflate", LayoutInflater::class.java).invoke(null, layoutInflater) as T


//Fragment
inline fun <reified T : ViewBinding> Fragment.inflate() = FragmentViewBindingDelegate(T::class.java)

class FragmentViewBindingDelegate<T : ViewBinding>(private val clazz: Class<T>) : ReadOnlyProperty<Fragment, T> {
  private var binding: T? = null

  @Suppress("UNCHECKED_CAST")
  override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
    if (binding == null) {
      binding = clazz.getMethod("bind", View::class.java).invoke(null, thisRef.requireView()) as T
      thisRef.viewLifecycleOwner.lifecycle.addObserver(object : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
          if (event == Lifecycle.Event.ON_DESTROY) binding = null
        }
      })
    }
    return binding!!
  }
}