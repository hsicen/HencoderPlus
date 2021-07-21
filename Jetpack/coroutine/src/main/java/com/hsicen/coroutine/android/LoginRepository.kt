package com.hsicen.coroutine.android

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

/**
 * 作者：hsicen  6/10/21 09:38
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class LoginRepository {
    private val loginUrl = "https://example.com/login"

    suspend fun makeLoginRequest(jsonBody: String): Result<LoginResponse> {
        return withContext(Dispatchers.IO) {
            val url = URL(loginUrl)
            (url.openConnection() as? HttpURLConnection)?.run {
                requestMethod = "GET"
                setRequestProperty("Content-Type", "application/json; utg-8")
                setRequestProperty("Accept", "application/json")
                doOutput = true
                outputStream.write(jsonBody.toByteArray())
                inputStream
            }

            Result.Success(LoginResponse("hsicen", "male", 27))
        }
    }
}

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

data class LoginResponse(val name: String, val gender: String, val age: Int)
