package com.hsicen.a6_retrofit.practise

import java.io.InputStream
import java.lang.reflect.Type

/**
 * @author: hsicen
 * @date: 2022/3/2 18:36
 * @email: codinghuang@163.com
 * description: used to transform a response to another response
 *
 * AS we all know, different backend may return different structs,
 * like {code, data, msg} or {status, data, msg} and so on. with [ResponseTransformer], we can
 * transform different data structs to one data struct.
 */
interface ResponseTransformer {

  /**
   * transform the [origin] inputStream to the common.
   * @param origin  the original response [InputStream]
   * @return  Another response [InputStream] in common data struct.
   */
  fun transform(origin: InputStream): InputStream


  /**
   * A [Factory] to create [ResponseTransformer]
   */
  interface Factory {

    /**
     * Create a [ResponseTransformer].
     * @param type the return type of method.
     * @return a instance of [ResponseTransformer].
     */
    fun create(type: Type): ResponseTransformer?
  }

}