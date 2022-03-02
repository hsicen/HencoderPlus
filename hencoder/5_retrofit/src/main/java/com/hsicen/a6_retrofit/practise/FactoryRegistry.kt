package com.hsicen.a6_retrofit.practise

/**
 * @author: hsicen
 * @date: 2022/3/2 18:32
 * @email: codinghuang@163.com
 * description: store factory and provide implements.
 *
 * please register factory before call [proxyRetrofit] method.
 */
object FactoryRegistry {
  private val responseTransformers = mutableListOf<ResponseTransformer.Factory>()
  private val


}