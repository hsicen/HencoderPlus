package com.hsicen.a6_retrofit.practise

import retrofit2.Retrofit
import java.lang.reflect.*
import kotlin.coroutines.Continuation


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
inline fun <reified T> T.proxyRetrofit(): T {
  val invocationHandler = Proxy.getInvocationHandler(this)
  return Proxy.newProxyInstance(
    T::class.java.classLoader, arrayOf(T::class.java)
  ) { proxy, method, args ->
    method.takeIf { it.isSuspendMethod }?.getSuspendReturnType()
      ?.let { FactoryRegistry}


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
