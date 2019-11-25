package com.hsicen.a23_rxjava

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * <p>作者：Hsicen  2019/10/21 8:41
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
interface ApiStore {
    @GET("/users/{user}/repos")
    fun listRepos(@Path("user") user: String): Single<Any>
}
