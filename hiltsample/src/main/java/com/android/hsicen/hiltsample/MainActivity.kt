package com.android.hsicen.hiltsample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.hsicen.hiltsample.builder.Book
import com.android.hsicen.hiltsample.data.User
import com.android.hsicen.hiltsample.databinding.ActivityMainBinding
import com.android.hsicen.hiltsample.factory.UserFactory
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.ExecutorService
import java.util.logging.Logger
import javax.inject.Inject

/**
 * 作者：hsicen  2020/8/14 11:02
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：Hilt依赖注入
 */

@AndroidEntryPoint //依赖注入标记
class MainActivity : AppCompatActivity() {
    //ViewBinding 替代findViewById
    private lateinit var binding: ActivityMainBinding

    @Inject //获取实例
    lateinit var mUser: User

    @Inject
    lateinit var mAny: Any

    @Inject
    lateinit var mExecutorService: ExecutorService

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val anyUser = mAny as User
        binding.tvUser.text = "${anyUser.name} 的心情是：${anyUser.mood}"
        mUser.mood = "有点想笑"

        mExecutorService.execute {
            Logger.getGlobal().info("Msg from ${Thread.currentThread().name}")
        }

        val book = Book.Builder()
            .id(2)
            .name("老人与海")
            .price(34.5f)
            .build()

        val newUser = UserFactory().newUser()
    }
}
