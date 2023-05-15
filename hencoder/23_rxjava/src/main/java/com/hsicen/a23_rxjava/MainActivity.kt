package com.hsicen.a23_rxjava

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a23_rxjava.databinding.ActivityMainBinding
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
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
    private lateinit var mBinding: ActivityMainBinding
    var mDisposable: Disposable? = null

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      mBinding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(mBinding.root)

      mBinding.btnGet.clicks().throttleFirst(800, TimeUnit.MILLISECONDS)
        .subscribe { observableInterval() }
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
        .doOnSubscribe { }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Any> {
                override fun onSuccess(t: Any) {
                    Log.d("hsc", t.toString())
                    mBinding.tvInfo.text = t.toString()
                }

                override fun onSubscribe(d: Disposable) {
                    //获取可取消对象，方便后续取消请求
                }

                override fun onError(e: Throwable) {
                    Log.d("hsc", "请求失败")
                    mBinding.tvInfo.text = "请求失败"
                }
            })
    }

    private fun singleJust() {
        var disposable: Disposable? = null

        Single.just(1)
            .map { it + 3 }
            .delay(3, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Int> {
                override fun onSuccess(t: Int) {
                    mBinding.tvInfo.text = "$t"
                    Log.d("hsc", "是否已经取消  ${disposable?.isDisposed}")
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                    mBinding.tvInfo.text = "开始"
                    Log.d("hsc", "是否已经取消  ${disposable?.isDisposed}")
                }

                override fun onError(e: Throwable) {
                    mBinding.tvInfo.text = "出错"
                }
            })
    }

    private fun observableInterval() {
      Observable.interval(1, TimeUnit.SECONDS)
        .delay(1L, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.computation())
            .map { it + 1 }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long> {
                override fun onComplete() {
                    mBinding.tvInfo.text = "结束"
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d("hsc", " 可取消对象： ${d.javaClass.name}")
                    Log.d("hsc", " 线程： " + Thread.currentThread().name)
                    mBinding.tvInfo.text = "开始"
                    mDisposable = d
                }

                override fun onNext(t: Long) {
                    Log.d("hsc", " 线程： " + Thread.currentThread().name)
                    mBinding.tvInfo.text = "$t"

                  /*if (10 == t.toInt()) {
                      mDisposable?.dispose()
                  }*/
                }

                override fun onError(e: Throwable) {
                    mBinding.tvInfo.text = "出错"
                }

            })
    }
}
