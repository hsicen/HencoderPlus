package com.hsicen.state

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StateViewModel : ViewModel() {

  val mStateName = MutableLiveData("hsicen")

  fun changeName(newName: String) {
    mStateName.postValue(newName)
  }
}