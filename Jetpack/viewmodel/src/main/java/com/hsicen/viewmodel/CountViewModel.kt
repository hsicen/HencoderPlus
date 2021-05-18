package com.hsicen.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

/**
 * 作者：hsicen  5/18/21 17:47
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class CountViewModel : ViewModel() {
  private val _count = MutableLiveData(0)
  private val mTimer by lazy { Timer() }
  private val mTimerTask by lazy {
    object : TimerTask() {
      override fun run() {
        _count.postValue(_count.value?.plus(1))
      }
    }
  }

  val count: LiveData<Int>
    get() = _count

  fun startTiming() {
    runCatching {
      mTimer.schedule(mTimerTask, 1000, 1000)
    }
  }

  override fun onCleared() {
    Log.d("hsc", "${this.javaClass.simpleName} onCleared()")
    super.onCleared()
  }
}