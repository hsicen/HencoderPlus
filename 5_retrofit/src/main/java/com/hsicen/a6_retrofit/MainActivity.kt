package com.hsicen.a6_retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a6_retrofit.entity.Repo
import com.hsicen.a6_retrofit.service.GithubService
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_fetch.setOnClickListener {
            fetchData()
        }
    }

    private fun fetchData() {
        /*val repoService = Net.instance().create(GithubService::class.java)
        repoService.listRepos("hsicen")
            .observeOn(Schedulers.newThread())
            .subscribe(object : SingleObserver<List<Repo>> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onSuccess(value: List<Repo>?) {

                }

                override fun onError(e: Throwable?) {
                }

            })*/

        /*val listRepos = repoService.listRepos("hsicen")
        listRepos.enqueue(object : Callback<List<Repo>> {
            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                tv_content.text = t.message
            }

            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                tv_content.text = response.body().toString()
            }
        })*/

    }
}
