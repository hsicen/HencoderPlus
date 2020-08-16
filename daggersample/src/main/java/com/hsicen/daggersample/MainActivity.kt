package com.hsicen.daggersample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.daggersample.data.User
import com.hsicen.daggersample.databinding.ActivityMainBinding
import javax.inject.Inject

/**
 * 作者：hsicen  2020/8/16 13:31
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：Dagger2依赖注入
 */
class MainActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityMainBinding

    @Inject
    lateinit var mUser: User

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        //手动加入依赖图
        (application as DaggerApp).coreComponent.inject(this)

        //mBinding.tvUser.text = "${mUser.name}使用Dagger的心情是：${mUser.mood}"
        mUser.mood = "难受"
    }
}