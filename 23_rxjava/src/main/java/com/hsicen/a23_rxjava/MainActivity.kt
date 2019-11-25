package com.hsicen.a23_rxjava

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_get.setOnClickListener { getUser() }
    }

    private fun getUser() {
        val mRetrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val apiStore = mRetrofit.create(ApiStore::class.java)
        apiStore.listRepos("hsicen")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Any> {
                override fun onSuccess(t: Any) {
                    Log.d("hsc", t.toString())
                    tv_info.text = t.toString()
                }

                override fun onSubscribe(d: Disposable) {
                    d.dispose()
                }

                override fun onError(e: Throwable) {
                    Log.d("hsc", "请求失败")
                    tv_info.text = "请求失败"
                }
            })
    }

    private fun singelJust() {
        Single.just(1)
            .map { it + 3 }
            .subscribe(object : SingleObserver<Int> {
                override fun onSuccess(t: Int) {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {

                }
            })
    }

    private fun observableInterval() {

    }

    override fun onResume() {
        super.onResume()

        btn_get.post {
            Log.d("hsc", "onResume 中 view.post 获取宽高信息：${btn_get.width} * ${btn_get.height}")
        }
    }
}
