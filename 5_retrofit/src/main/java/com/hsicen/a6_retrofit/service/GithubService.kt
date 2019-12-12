package com.hsicen.a6_retrofit.service

import com.hsicen.a6_retrofit.entity.Repo
import com.hsicen.a6_retrofit.entity.User
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * <p>作者：Hsicen  2019/10/8 14:42
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：Web Service接口
 */
interface GithubService {
    @GET("/users/{user}/repos")
    fun listRepos(@Path("user") user: String): Single<List<Repo>>

    @GET("/users/hsicen")
    fun getUser(): Call<User>
}