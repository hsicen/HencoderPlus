package com.hsicen.a6_retrofit.practise

import java.lang.reflect.Type

/**
 * @author: hsicen
 * @date: 2022/3/2 18:49
 * @email: codinghuang@163.com
 * description: used to resolve [Throwable] when a suspend method throw [Throwable] in run time.
 *
 * @param T a type the [ThrowableResolver] return after resolving.
 */
interface ThrowableResolver<T> {

  /**
   * Resolve [Throwable]
   * @param throwable throwable to resolve.
   * @return T resolved result.
   */
  fun resolve(throwable: Throwable): T


  /**
   * A factory to create [ThrowableResolver]
   */
  interface Factory<T> {

    /**
     * Create a [ThrowableResolver]
     * @param type the suspend method's return type.
     * @return an instance of [ThrowableResolver]
     */
    fun create(type: Type): ThrowableResolver<T>?
  }
}
