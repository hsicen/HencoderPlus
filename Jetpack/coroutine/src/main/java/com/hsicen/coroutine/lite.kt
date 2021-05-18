package com.hsicen.coroutine

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * 作者：hsicen  5/5/21 8:13 PM
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

//1.实现一个delay函数
private val executor = Executors.newScheduledThreadPool(1) { runnable ->
  Thread(runnable, "Scheduler").apply { isDaemon = true }
}

suspend fun delay(time: Long, unit: TimeUnit = TimeUnit.MILLISECONDS) {
  if (time <= 0) {
    return
  } else {
    suspendCoroutine<Unit> { continuation ->
      executor.schedule({ continuation.resume(Unit) }, time, unit)
    }
  }
}

//2.协程的描述类
typealias OnComplete = () -> Unit
typealias OnCancel = () -> Unit


interface Job : CoroutineContext.Element {
  companion object Key : CoroutineContext.Key<Job>

  override val key: CoroutineContext.Key<*> get() = Job

  val isActive: Boolean

  //fun invokeOnCancel(onCancel: OnCancel): Disposable

  //fun invokeOnComplete(onComplete: OnComplete): Disposable

  fun cancel()

  //fun remove(disposable: Disposable)

  suspend fun join()
}

sealed class CoroutineState {
  class Incomplete : CoroutineState()
  class Cancelling : CoroutineState()
  class Complete<T>(val value: T? = null, val exception: Throwable? = null) : CoroutineState()
}