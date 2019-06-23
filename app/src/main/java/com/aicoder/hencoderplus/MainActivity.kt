package com.aicoder.hencoderplus

import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {

    private val lazyInt by lazy {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        val resulr = 1 ushr 2

        16f.px
    }


    private val Float.px: Float
        get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)

    private inline fun <reified T> create(): T {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.baidu.com")
            .build()

        return retrofit.create(T::class.java)
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.baidu.com")
            .build()

        val githubService = retrofit.create(GithubService::class.java)
        val service = create<GithubService>()


        val repos = githubService.getRepos("hsc")
        repos.enqueue(object : Callback<List<Repo>> {
            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                //请求失败

            }

            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                //请求成功

            }
        })
    }
}
