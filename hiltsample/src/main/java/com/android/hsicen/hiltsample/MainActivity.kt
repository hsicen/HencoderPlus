package com.android.hsicen.hiltsample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.hsicen.hiltsample.data.User
import com.android.hsicen.hiltsample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * 作者：hsicen  2020/8/14 11:02
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：Hilt依赖注入
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //ViewBinding 替代findViewById
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var mUser: User

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvUser.text = "${mUser.name} 的心情是：${mUser.mood}"
        mUser.mood = "有点想笑"
    }
}
