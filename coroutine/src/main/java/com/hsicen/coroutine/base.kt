package com.hsicen.coroutine

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlin.coroutines.*

/**
 * 作者：hsicen  5/5/21 10:47 AM
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：简单协程的使用
 */

//1：协程的创建
val continuation = suspend {
    println("In Coroutine. ${coroutineContext[CoroutineName]}")
    5
}.createCoroutine(object : Continuation<Int> {

    override val context: CoroutineContext = EmptyCoroutineContext

    override fun resumeWith(result: Result<Int>) {
        println("Coroutine end: $result")
    }
})

//3.协程体的Receiver
fun <R, T> launchCoroutine(receiver: R, block: suspend R.() -> T) {
    block.startCoroutine(receiver, object : Continuation<T> {
        override val context: CoroutineContext = EmptyCoroutineContext

        override fun resumeWith(result: Result<T>) {
            println("Coroutine end: $result")
        }
    })
}

class ProducerScope<T> {
    suspend fun produce(value: T) {
        println("Produce $value")
    }
}

fun callLaunchCoroutine() {
    launchCoroutine(ProducerScope<Int>()) {
        println("In coroutine.")
        produce(1024) //挂起点
        delay(1000)
        produce(2048) //挂起点
    }
}

//4.上下文
class CoroutineName(val name: String) : AbstractCoroutineContextElement(Key) {
    companion object Key : CoroutineContext.Key<CoroutineName>
}

class CoroutineExceptionHandler(val onErrorAction: (Throwable) -> Unit) :
    AbstractCoroutineContextElement(Key) {
    companion object Key : CoroutineContext.Key<CoroutineExceptionHandler>

    fun onError(error: Throwable) {
        error.printStackTrace()
        onErrorAction(error)
    }
}

//5.拦截器
class LogInterceptor : ContinuationInterceptor {
    override val key: CoroutineContext.Key<*>
        get() = ContinuationInterceptor

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        return LogContinuation(continuation)
    }
}

class LogContinuation<T>(private val continuation: Continuation<T>) :
    Continuation<T> by continuation {

    override fun resumeWith(result: Result<T>) {
        println("Before resume: $result")
        continuation.resumeWith(result)
        println("After resume: $result")
    }
}

fun main() {
    suspend {
        //do sth
        3
    }.startCoroutine(object : Continuation<Int> {
        override val context: CoroutineContext
            get() = LogInterceptor()

        override fun resumeWith(result: Result<Int>) {
            //do sth
        }
    })

    //2.协程的启动
    continuation.resume(Unit)
}
