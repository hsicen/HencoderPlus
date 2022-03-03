package com.hsicen.a6_retrofit.practise

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.InputStream
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


/**
 * Step1: define base api result.
 *
 * @param T
 * @property code Int
 * @property data T?
 * @property msg String?
 * @property isSuccess Boolean
 * @property failure Failure?
 * @constructor
 */
data class BaseAPIResult<T>(
  @SerializedName("code") val code: Int,
  @SerializedName("data") override val data: T? = null,
  @SerializedName("msg") val msg: String? = ""
) : APIResult<T, BaseAPIResult.Failure> {
  data class Failure(val code: Int, val msg: String) : APIResult.Failure

  override val isSuccess: Boolean = code == 0

  override val failure: Failure? = if (isFailure) Failure(code, msg ?: "") else null
}

/**
 * Step2: define a [ThrowableResolver] to handle throwable and return a result.
 */
class BaseThrowableResolveFactory : ThrowableResolver.Factory<BaseAPIResult<*>> {

  override fun create(type: Type): ThrowableResolver<BaseAPIResult<*>>? {
    return (type as? ParameterizedType)?.rawType
      ?.takeIf { it == BaseAPIResult::class.java }
      ?.let { Resolver() }
  }

  inner class Resolver : ThrowableResolver<BaseAPIResult<*>> {
    override fun resolve(throwable: Throwable): BaseAPIResult<*> {
      // return specific result when throw [Throwable]
      return BaseAPIResult<Any>(-1, null, throwable.message ?: "")
    }
  }
}

/**
 * Step3. define a [WanAndroidResponseTransformerFactory] to transform different response data
 * to the same response data struct.
 */
class WanAndroidResponseTransformerFactory : ResponseTransformer.Factory {
  companion object {
    private val gson = Gson()
  }

  override fun create(type: Type): ResponseTransformer? {
    return (type as? ParameterizedType)?.rawType
      ?.takeIf { it == BaseAPIResult::class.java }
      ?.let { Transformer() }
  }

  inner class WanAndroidResult<T> {
    @SerializedName("code", alternate = ["errorCode"])
    private val errorCode: Int = -1

    @SerializedName("data")
    private val data: T? = null

    @SerializedName("message", alternate = ["errorMsg"])
    private val errorMsg: String = ""
  }

  inner class Transformer : ResponseTransformer {
    override fun transform(origin: InputStream): InputStream {
      val response = gson.fromJson<WanAndroidResult<JsonElement>>(
        origin.reader(),
        object : TypeToken<WanAndroidResult<JsonElement>>() {}.type
      )

      return gson.toJson(response).byteInputStream()
    }
  }
}


interface WanAndroidService {
  @GET("/test")
  suspend fun test(): BaseAPIResult<JsonElement>

  @GET("/user/lg/userinfo/json")
  suspend fun userInfo(): BaseAPIResult<JsonElement>

  @GET("/banner/json")
  suspend fun banner(): BaseAPIResult<JsonElement>
}

class PractiseTest {
  /**
   * Step4: add [TransformConverterFactory] to [Retrofit] Converter.Factory list at first.
   */
  private val retrofit by lazy {
    Retrofit.Builder()
      .baseUrl("https://www.wanandroid.com/")
      .addConverterFactory(TransformConverterFactory())
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  /**
   * Step5: register custom ThrowableResolver and ResponseTransformer.
   */
  init {
    FactoryRegistry.registerThrowableResolver(BaseThrowableResolveFactory())
    FactoryRegistry.registerResponseTransformer(WanAndroidResponseTransformerFactory())
  }

  fun test() {

    /**
     * Step6: call [proxyRetrofit] after calling [Retrofit.create].
     */
    val service = retrofit.create(WanAndroidService::class.java)
      .proxyRetrofit()

    runBlocking {
      service.test()
        .onSuccess { println("execute test success ==> $it") }
        .onFailure { println("execute test failure ==> $it") }
        // userInfo
        .onFailureThen { service.userInfo() }
        ?.onSuccess { println("execute userInfo success ==> $it") }
        ?.onFailure { println("execute userInfo failure ==> $it") }
        // banner
        ?.onFailureThen { service.banner() }
        ?.onSuccess { println("execute banner success ==> $it") }
        ?.onFailure { println("execute banner failure ==> $it") }
    }
  }
}