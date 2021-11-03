package com.hsicen.a6_okhttp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import android.view.SurfaceHolder
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a6_okhttp.databinding.ActivityMainBinding
import com.hsicen.a6_okhttp.interceptor.LoggingInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSink
import java.io.File
import java.io.IOException
import java.lang.reflect.Type
import java.util.*
import kotlin.concurrent.thread

/**
 * <p>作者：hsicen  2019/12/10 19:51
 * <p>邮箱：codinghuang@163.com
 * <p>功能：
 * <p>描述：[OkHttp 分析](https://square.github.io/okhttp/)
 *The HTTP client’s job is to accept your request and produce its response.
 *
 * 3.12.x   以上需要5.0(21) 和 java8
 * 3.12.x   以下支持2.3+(9) 和 java7  (维护到2020年底)
 *
 * R8 混淆规则
 * https://square.github.io/okhttp/r8_proguard/
 */
@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnRequest.setOnClickListener { getCall() }

    binding.btnRequest.setOnClickListener {
      binding.tvInfo.requestLayout()
      thread {
        binding.tvInfo.text = Thread.currentThread().name
      }

      thread {
        Looper.prepare()
        Toast.makeText(this, "Hello world", Toast.LENGTH_SHORT).show()

        val button = Button(this)
        windowManager.addView(button, WindowManager.LayoutParams())
        button.setOnClickListener {
          button.text = Thread.currentThread().name
        }

        Looper.loop()
      }
    }

    binding.svHolder.holder.addCallback(object : SurfaceHolder.Callback {
      override fun surfaceCreated(holder: SurfaceHolder) {
        thread {
          while (true) {
            holder.lockCanvas()?.let {
              val random = Random()
              val red = random.nextInt(255)
              val green = random.nextInt(255)
              val blue = random.nextInt(255)
              it.drawColor(Color.rgb(red, green, blue))
              holder.unlockCanvasAndPost(it)
              SystemClock.sleep(500)
            }
          }
        }
      }

      override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
      }

      override fun surfaceDestroyed(holder: SurfaceHolder) {

      }
    })

  }

  /*** 响应缓存处理*/
  private fun responseCache() {
    val client = OkHttpClient.Builder()
      .cache(Cache(directory = cacheDir, maxSize = 10L * 1024 * 1024))
      .build()

    val request = Request.Builder()
      .url("http://publicobject.com/helloworld.txt")
      .build()

    client.newCall(request).enqueue(object : Callback {
      override fun onFailure(call: Call, e: IOException) {
        dealFailed(e)
      }

      override fun onResponse(call: Call, response: Response) {
        response.use {
          if (!it.isSuccessful) throw IOException("请求出错")

          Log.d("hsc", "response:     $response")
          Log.d("hsc", "cache:     ${response.cacheResponse}")
          Log.d("hsc", "network:     ${response.networkResponse}")
        }
      }
    })
  }


  /*** 序列化和反序列化处理*/
  private fun jsonParse() {
    val client = OkHttpClient()
    val moshi = Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .build()

    //parse object
    //val adapter = moshi.adapter(User::class.java)
    //val userJson = adapter.toJson(User("hsicen", 23))
    //val user = adapter.fromJson(userJson)

    //parse array
    val type: Type = Types.newParameterizedType(List::class.java, Repos::class.java)
    val adapter = moshi.adapter<List<Repos>>(type)

    val request = Request.Builder()
      .url("https://api.github.com/users/hsicen/repos")
      .get()
      .build()

    client.newCall(request).enqueue(object : Callback {
      override fun onFailure(call: Call, e: IOException) {
        dealFailed(e)
      }

      override fun onResponse(call: Call, response: Response) {
        if (response.isSuccessful) {
          val repos = adapter.fromJson(response.body!!.source())
          runOnUiThread { binding.tvInfo.text = repos.toString() }
        } else {
          throw IOException("请求出错：$response")
        }
      }
    })

  }

  private fun getCall() {
    var resultMsg: String

    val okHttpClient = OkHttpClient.Builder()
      .certificatePinner(
        CertificatePinner.Builder()
          .build()
      )
      .addInterceptor(LoggingInterceptor())
      .build()

    val getRequest = Request.Builder()
      .url("https://api.github.com/users/hsicen/repos")
      .header("User-Agent", "OkHttp Test")
      .get()
      //.header()  //会替换对应header
      //.addHeader()  //不会替换
      .build()

    val executeCall = okHttpClient.newCall(getRequest)
    val enqueueCall = executeCall.clone()

    // enqueue 异步回调(会自动切线程，但是不会回切)
    enqueueCall.enqueue(object : Callback {
      override fun onFailure(call: Call, e: IOException) {
        resultMsg = e.message ?: e.javaClass.name
        runOnUiThread { binding.tvInfo.text = "异步：$resultMsg" }
      }

      override fun onResponse(call: Call, response: Response) {
        resultMsg = response.body?.string() ?: response.message
        runOnUiThread { binding.tvInfo.text = "异步：$resultMsg" }
      }
    })

    // execute 同步请求    会阻塞主线程(异常)
    thread {
      executeCall.execute().use {
        val resultMsg =
          if (!it.isSuccessful) "出错${it.message}"
          else "成功${it.body?.string()}"

        runOnUiThread { binding.tvInfo.text = "同步：$resultMsg" }
      }
    }
  }

  private fun postCall() {
    val client = OkHttpClient()

    //postString(client) //body 小于1M适用
    //postStream(client) //body 较大
    //postFile(client)  //文件传输
    //postParameters(client)  //表单数据
    postMultipart(client)    //多种参数类型表单数据
  }

  private fun postMultipart(client: OkHttpClient) {
    val formData = MultipartBody.Builder()
      .setType(MultipartBody.FORM)
      .addFormDataPart("title", "Square Logo")
      .addFormDataPart(
        "image", "logo-square.png",
        File("README.md").asRequestBody(MEDIA_TYPE_MARKDOWN)
      )
      .build()

    val request = Request.Builder()
      .header("Authorization", "Client-ID $IMGUR_CLIENT_ID")
      .url("https://api.imgur.com/3/image")
      .post(formData)
      .build()

    client.newCall(request).enqueue(object : Callback {
      override fun onFailure(call: Call, e: IOException) {
        dealFailed(e)
      }

      override fun onResponse(call: Call, response: Response) {
        dealResponse(response)
      }
    })
  }

  /*** 表单数据*/
  private fun postParameters(client: OkHttpClient) {
    val formBody = FormBody.Builder()
      .add("search", "Jurassic Park")
      .build()

    val request = Request.Builder()
      .url("https://en.wikipedia.org/index.ph")
      .post(formBody)
      .build()

    client.newCall(request).enqueue(object : Callback {
      override fun onFailure(call: Call, e: IOException) {
        dealFailed(e)
      }

      override fun onResponse(call: Call, response: Response) {
        dealResponse(response)
      }
    })
  }

  private fun postFile(client: OkHttpClient) {
    val file = File("README.md")

    val request = Request.Builder()
      .url("https://api.github.com/markdown/raw")
      .post(file.asRequestBody(MEDIA_TYPE_MARKDOWN))
      .build()

    client.newCall(request).enqueue(object : Callback {
      override fun onFailure(call: Call, e: IOException) {
        dealFailed(e)
      }

      override fun onResponse(call: Call, response: Response) {
        dealResponse(response)
      }
    })

  }

  private fun postStream(client: OkHttpClient) {
    val requestBody = object : RequestBody() {
      override fun contentType() = MEDIA_TYPE_MARKDOWN

      override fun writeTo(sink: BufferedSink) {
        sink.writeUtf8("Numbers\n")
        sink.writeUtf8("-------\n")

        for (i in 2..997) {
          sink.writeUtf8(String.format(" * $i = ${factor(i)}\n"))
        }
      }

      private fun factor(n: Int): String {
        for (i in 2 until n) {
          val x = n / i
          if (x * i == n) return "${factor(x)} × $i"
        }

        return n.toString()
      }
    }

    val request = Request.Builder()
      .url("https://api.github.com/markdown/raw")
      .post(requestBody)
      .build()

    client.newCall(request).enqueue(object : Callback {
      override fun onFailure(call: Call, e: IOException) {
        e.printStackTrace()
      }

      override fun onResponse(call: Call, response: Response) {
        dealResponse(response)
      }
    })
  }

  private fun postString(client: OkHttpClient) {
    val postBody = """
        |Releases
        |--------
        |
        | * _1.0_ May 6, 2013
        | * _1.1_ June 15, 2013
        | * _1.2_ August 11, 2013
        |""".trimMargin()

    val request = Request.Builder()
      .url("https://api.github.com/markdown/raw")
      .post(postBody.toRequestBody(MEDIA_TYPE_MARKDOWN))
      .build()

    client.newCall(request).enqueue(object : Callback {
      override fun onFailure(call: Call, e: IOException) {
        e.printStackTrace()
      }

      override fun onResponse(call: Call, response: Response) {
        dealResponse(response)
      }
    })
  }

  private fun dealResponse(response: Response) {
    response.use {
      if (it.isSuccessful) {
        Log.d("hsc", "请求成功：${it.body?.string()}")
      } else {
        Log.d("hsc", "请求失败：${it.javaClass.name}")
      }
    }
  }

  private fun dealFailed(e: IOException) {
    Log.d("hsc", "请求失败： ${e.message ?: e.javaClass.name}")
  }

  companion object {
    private val IMGUR_CLIENT_ID = "9199fdef135c122"

    private val MEDIA_TYPE_MARKDOWN = "text/x-markdown; charset=utf-8".toMediaType()
    private val MEDIA_TYPE_PNG = "image/png".toMediaType()

  }

}
