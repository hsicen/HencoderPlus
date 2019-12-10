package com.hsicen.a23_rxjava

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * <p>作者：hsicen  2019/12/5 9:11
 * <p>邮箱：codinghuang@163.com
 * <p>功能：
 * <p>描述：RxJava2原理解析
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_get.setOnClickListener { observableInterval() }
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
                    //获取可取消对象，方便后续取消请求
                }

                override fun onError(e: Throwable) {
                    Log.d("hsc", "请求失败")
                    tv_info.text = "请求失败"
                }
            })
    }

    private fun singelJust() {
        var disposable: Disposable? = null

        Single.just(1)
            .map { it + 3 }
            .delay(3, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Int> {
                override fun onSuccess(t: Int) {
                    tv_info.text = "$t"
                    Log.d("hsc", "是否已经取消  ${disposable?.isDisposed}")
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                    tv_info.text = "开始"
                    Log.d("hsc", "是否已经取消  ${disposable?.isDisposed}")
                }

                override fun onError(e: Throwable) {
                    tv_info.text = "出错"
                }
            })
    }

    private fun observableInterval() {
        Observable.interval(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.computation())
            .map { it + 1 }
            .delay(30, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long> {
                override fun onComplete() {
                    tv_info.text = "结束"
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d("hsc", " 可取消对象： ${d.javaClass.name}")
                    Log.d("hsc", " 线程： " + Thread.currentThread().name)
                    tv_info.text = "开始"
                }

                override fun onNext(t: Long) {
                    Log.d("hsc", " 线程： " + Thread.currentThread().name)
                    tv_info.text = "$t"
                }

                override fun onError(e: Throwable) {
                    tv_info.text = "出错"
                }

            })
    }
}
