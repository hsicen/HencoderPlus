package com.hsicen.coroutine.basic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 作者：hsicen  6/1/21 17:40
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

fun simple(): Flow<Int> = flow { // flow builder
  for (i in 1..3) {
    delay(100) // pretend we are doing something useful here
    emit(i) // emit next value
  }
}

fun main(): Unit = runBlocking {
  // Launch a concurrent coroutine to check if the main thread is blocked
  launch {
    for (k in 1..3) {
      println("I'm not blocked $k")
      delay(100)
    }
  }
}

// flow example
data class News(val id: String, val topic: String)
class UserData(var id: String, var topic: String) {
  fun isFavoriteTopic(news: News) = news.topic == topic
}

interface NewsApi {
  suspend fun fetchLatestNews(): List<News>
}

class NewsRemoteDataSource(
  private val refreshIntervalMs: Long = 5000
) {
  private val newsApi = object : NewsApi {
    override suspend fun fetchLatestNews(): List<News> {
      val id = System.currentTimeMillis()
      return listOf(News("$id", "topic$id"))
    }
  }

  val latestNews: Flow<List<News>> = flow {
    while (true) {
      val latestNews = newsApi.fetchLatestNews()
      emit(latestNews)
      delay(refreshIntervalMs)
    }
  }.flowOn(Dispatchers.IO) //the upstream flow will execute on the io dispatcher
}

class NewsRepository(
  private val userData: UserData
) {
  private val newsRemoteDataSource by lazy { NewsRemoteDataSource() }

  val favoriteNews: Flow<List<News>> =
    newsRemoteDataSource.latestNews
      .map { news -> news.filter { userData.isFavoriteTopic(it) } } //Executes on the io dispatcher
      .onEach { news -> saveInCache(news) } //Executes on the io dispatcher
      //flowOn affects the upstream flow ↑
      .flowOn(Dispatchers.IO)
      //the downstream flow ↓ is not affected
      .catch { exception -> emit(lastCachedNews()) } //Executes in the consumer's context

  private fun lastCachedNews(): List<News> {
    return listOf(News("cacheId", "cacheNews"))
  }

  private fun saveInCache(news: List<News>) {
    //Save news in disk cache
  }
}

class NewsViewModel(userData: UserData) : ViewModel() {
  private val newsRepository by lazy { NewsRepository(userData) }

  fun fetchFavoriteNews() {
    viewModelScope.launch {
      newsRepository.favoriteNews
        .catch { exception -> notifyError(exception) }
        .collect { news ->
          //update view with the latest news data
        }
    }
  }

  private fun notifyError(exception: Throwable) {
    //on error handle
  }
}

class FirebaseFirestore
class UserEvents

class FirestoreUserEventDataSource(
  private val firestore: FirebaseFirestore
) {

  fun getUserEvents(): Flow<UserEvents> = callbackFlow {


    awaitClose { } //close action
  }
}

