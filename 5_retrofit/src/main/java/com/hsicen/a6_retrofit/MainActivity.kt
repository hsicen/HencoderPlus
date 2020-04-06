package com.hsicen.a6_retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a6_retrofit.entity.Repo
import com.hsicen.a6_retrofit.entity.User
import com.hsicen.a6_retrofit.service.GithubService
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_fetch.setOnClickListener {
            fetchData()
        }
    }

    private fun fetchData() {
        val repoService = Net.instance().create(GithubService::class.java)

        repoService.listRepos("hsicen")
            .observeOn(Schedulers.newThread())
            .subscribe(object : SingleObserver<List<Repo>> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onSuccess(value: List<Repo>?) {

                }

                override fun onError(e: Throwable?) {
                }

            })

        val listRepos = repoService.getUser()
        val cloneCall = listRepos.clone()

        listRepos.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {

            }

            override fun onResponse(call: Call<User>, response: Response<User>) {

            }
        })

        thread {
            val userResponse = cloneCall.execute()
        }
    }
}
