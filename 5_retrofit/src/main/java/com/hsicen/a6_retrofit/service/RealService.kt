package com.hsicen.a6_retrofit.service

import com.hsicen.a6_retrofit.entity.Repo
import com.hsicen.a6_retrofit.entity.User
import io.reactivex.Single
import okhttp3.internal.platform.Platform
import retrofit2.Call
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

/**
 * <p>作者：Hsicen  2019/10/8 17:49
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
class RealService : GithubService {

  private val invocationHandler = object : InvocationHandler {
    private val platform = Platform.get()
    private val emptyArgs = arrayOfNulls<Any>(0)

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
      //扮演代理角色，对不同的方法做不同的处理

      return Any()
    }
  }

  override fun listRepos(user: String): Single<List<Repo>> {
    val method = GithubService::class.java.getMethod("listRepos", String::class.java)
    return invocationHandler.invoke(this, method, arrayOf(user)) as Single<List<Repo>>
  }

  override fun getUser(): Call<User> {
    val method = GithubService::class.java.getMethod("getUser")
    return invocationHandler.invoke(this, method, null) as Call<User>
  }

}