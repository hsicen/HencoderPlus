package com.hsicen.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 作者：hsicen  5/22/21 16:55
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class LiveViewModel : ViewModel() {
  private val _user = MutableLiveData<User>()
  val user: LiveData<User>
    get() = _user

  fun changeUser() {
    val user1 = User("hsicen", "male", (System.currentTimeMillis().mod(30)))
    _user.postValue(user1)
  }
}