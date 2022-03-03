package com.hsicen.a6_retrofit.practise

import java.lang.reflect.Type

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
  private val throwableResolvers = mutableListOf<ThrowableResolver.Factory<*>>()


  /******======ResponseTransformer======******/
  fun registerResponseTransformer(factory: ResponseTransformer.Factory) {
    responseTransformers.add(factory)
  }

  fun getResponseTransformer(rawType: Type): ResponseTransformer? {
    responseTransformers.forEach {
      val transformer = it.create(rawType)
      if (null != transformer) return transformer
    }

    return null
  }


  /******=======ThrowableResolver======******/
  fun registerThrowableResolver(factory: ThrowableResolver.Factory<*>) {
    throwableResolvers.add(factory)
  }

  fun getThrowableResolver(type: Type): ThrowableResolver<*>? {
    throwableResolvers.forEach {
      val resolver = it.create(type)
      if (resolver != null) return resolver
    }

    return null
  }
}
