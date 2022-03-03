package com.hsicen.a6_retrofit.practise

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * @author: hsicen
 * @date: 2022/3/3 16:23
 * @email: codinghuang@163.com
 * description: A [TransformConverterFactory] to transform different data struct to one.
 *
 * Please put [TransformConverterFactory] at the first place in [Retrofit] [Converter.Factory] lists.
 * ```kotlin
 *  Retrofit.Builder()
 *    .baseUrl("xxxxxxxx")
 *    .addConverterFactory(TransformConverterFactory())
 *    .addConverterFactory(GsonConverterFactory.create())
 *    .build()
 * ```
 */
class TransformConverterFactory : Converter.Factory() {

  override fun responseBodyConverter(
    type: Type,
    annotations: Array<out Annotation>,
    retrofit: Retrofit
  ): Converter<ResponseBody, *>? {
    return FactoryRegistry.getResponseTransformer(type)?.let { transformer ->
      TransformConverter(
        retrofit.nextResponseBodyConverter(this, type, annotations),
        transformer
      )
    }
  }


  inner class TransformConverter(
    private val baseConverter: Converter<ResponseBody, Any>,
    private val responseTransformer: ResponseTransformer
  ) : Converter<ResponseBody, Any> {

    override fun convert(value: ResponseBody): Any? {
      val transformed = ResponseBody.create(
        value.contentType(),
        responseTransformer.transform(value.byteStream()).readBytes()
      )

      return baseConverter.convert(transformed)
    }
  }
}