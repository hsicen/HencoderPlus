package com.hsicen.a6_retrofit.practise

import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import java.lang.reflect.*
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

/**
 * @author: hsicen
 * @date: 2022/3/2 17:53
 * @email: codinghuang@163.com
 * description: Proxy the interface created by [Retrofit.create] again.
 * if the method invoked is a suspend method, it can intercept exception by [ThrowableResolver],
 * so we don't need to add try-catch when we call suspend method.
 *
 * @param T an interface generic type
 * @return an instance of re-proxy interface
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T> T.proxyRetrofit(): T {
  val invocationHandler = Proxy.getInvocationHandler(this)

  return Proxy.newProxyInstance(
    T::class.java.classLoader, arrayOf(T::class.java)
  ) { proxy, method, args ->
    method.takeIf { it.isSuspendMethod }?.getSuspendReturnType()
      ?.let { FactoryRegistry.getThrowableResolver(it) }
      ?.let { resolver ->
        args.updateAt(
          args.lastIndex, FakeSuccessContinuationWrapper(
            args.last() as Continuation<Any>,
            resolver as ThrowableResolver<Any>
          )
        )
      }

    invocationHandler.invoke(proxy, method, args)
  } as T
}


/**
 * A property to indicate where the method is a suspend method or not.
 */
val Method.isSuspendMethod: Boolean
  get() = genericParameterTypes.lastOrNull()
    ?.let { it as? ParameterizedType }?.rawType == Continuation::class.java


/**
 * Get a suspend method return type, if the method is not a suspend method return null.
 *
 * @return return type of the suspend method.
 */
fun Method.getSuspendReturnType(): Type? {
  return genericParameterTypes.lastOrNull()
    ?.let { it as? ParameterizedType }?.actualTypeArguments?.firstOrNull()
    ?.let { it as? WildcardType }?.lowerBounds?.firstOrNull()
}


/**
 * Update Array at a special index.
 *
 * @param index the index to update
 * @param updated the updated value
 */
fun Array<Any?>.updateAt(index: Int, updated: Any?) {
  this[index] = updated
}

/**
 * A [FakeSuccessContinuationWrapper] which can resume a fake success when catch a [Throwable]
 *
 * @param T return type of the suspend method.
 * @property original the original Continuation.
 * @property throwableResolver [ThrowableResolver] is used to resolve [Throwable]
 * @property context CoroutineContext
 * @constructor
 */
class FakeSuccessContinuationWrapper<T>(
  private val original: Continuation<T>,
  private val throwableResolver: ThrowableResolver<T>
) : Continuation<T> {

  override val context: CoroutineContext = original.context

  override fun resumeWith(result: Result<T>) {
    runBlocking(context) {
      result.onSuccess {
        // when it's success, resume with original continuation
        original.resumeWith(result)
      }.onFailure {
        // when it's failure, resume a wrapper success with contain failure
        val fakeResult = throwableResolver.resolve(it)
        original.resumeWith(Result.success(fakeResult))
      }
    }
  }
}
