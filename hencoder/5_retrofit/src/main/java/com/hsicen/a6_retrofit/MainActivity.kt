package com.hsicen.a6_retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a6_retrofit.databinding.ActivityMainBinding
import com.hsicen.a6_retrofit.entity.Repo
import com.hsicen.a6_retrofit.entity.User
import com.hsicen.a6_retrofit.practise.PractiseTest
import com.hsicen.a6_retrofit.service.GithubService
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 作者：hsicen  2020/8/27 9:12
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：Retrofit源码解析
 *
 * 关键点：
 * 1. Retrofit.create(GithubService::class.java)
 *  创建出Service对象，让我们可以调用在Service里定义的方法进行接口请求
 *  create()方法内部是通过动态代理的方法创建的，程序会在运行时动态创建一个类去实现Service接口，Service里的每个抽象方法
 *  都会代理给InvocationHandler去处理，InvocationHandler在Invoke()方法中会分类去处理各种方法，我们在接口里定义的抽
 *  象方法主要是 loadServiceMethod(method).invoke(args) 去处理
 *
 * 2. Retrofit.loadServiceMethod(method) -> ServiceMethod 的创建
 *  这个方法有缓存机制，会从cache中拿两次，拿不到再手动创建 -> ServiceMethod.parseAnnotations(this, method)
 *  RequestFactory.parseAnnotations(retrofit, method) 解析注解(包括method,url,header等)，创建一个RequestFactory对象
 *  HttpServiceMethod.parseAnnotations(retrofit, method, requestFactory)
 *
 * 3. OkHttpCall的创建： 在第二步创建的是HttpServiceMethod，则HttpServiceMethod.invoke(args)
 *  在invoke()方法中创建了一个OkHttpCall，OkHttpCall是Retrofit的Call的子类；也就是我们在Service接口中定义的方法的返回类型Call.
 *  在它的enqueue或execute方法调用时，会创建一个okhttp3.Call，然后调用其enqueue或execute方法，并在其回调中回调OkHttpCall的Callback
 *
 * 4. HttpServiceMethod.invoke(args)中的adapt()方法
 *  这个方法是用来对OkHttpCall进行转换的，系统默认返回的是一个ExecutorCallbackCall，它的作用是把操作切回主线程，在主线程中回调网络
 *  请求结果；这个方法用来结合自定义的CallAdaptor来实现Call的转换,例如结合RxJava来返回一个Observable对象
 */
class MainActivity : AppCompatActivity() {
  lateinit var bing: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    bing = ActivityMainBinding.inflate(layoutInflater)
    setContentView(bing.root)

    bing.btnFetch.setOnClickListener {
      testPractise()
    }
  }

  private fun fetchData() {
    val repoService = Net.instance().create(GithubService::class.java)

    repoService.listRepos("hsicen")
      .observeOn(Schedulers.newThread())
      .subscribe(object : SingleObserver<List<Repo>> {
        override fun onSubscribe(d: Disposable?) {}
        override fun onSuccess(value: List<Repo>?) {}
        override fun onError(e: Throwable?) {}
      })

    val listRepos = repoService.getUser()
    val cloneCall = listRepos.clone()

    listRepos.enqueue(object : Callback<User> {
      override fun onFailure(call: Call<User>, t: Throwable) {}
      override fun onResponse(call: Call<User>, response: Response<User>) {}
    })
  }

  private fun testPractise() {
    val test = PractiseTest()
    test.test()
  }
}
