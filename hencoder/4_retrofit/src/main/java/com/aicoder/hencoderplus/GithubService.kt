package com.aicoder.hencoderplus

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubService {

  @GET("users/{user}/repos")
  fun getRepos(@Path("user") userName: String): Call<List<Repo>>

}