package com.hsicen.coroutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsicen.coroutine.basic.News
import com.hsicen.coroutine.basic.NewsRepository
import com.hsicen.coroutine.basic.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * 作者：hsicen  6/2/21 17:39
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：FlowViewModel
 */
class FlowViewModel : ViewModel() {
  private val newsRepository by lazy { NewsRepository(UserData("1", "comic")) }

  private val _uiState = MutableStateFlow<LoadState>(LoadState.Initial)
  val uiState: StateFlow<LoadState> = _uiState

  fun fetchNews() {
    viewModelScope.launch {
      newsRepository.favoriteNews
        .catch {
          _uiState.value = LoadState.Error(it)
        }.collect {
          _uiState.value = LoadState.Loaded(it)
        }
    }
  }
}

sealed class LoadState {
  object Initial : LoadState()
  data class Loaded(val news: List<News>) : LoadState()
  data class Error(val exception: Throwable) : LoadState()
}
