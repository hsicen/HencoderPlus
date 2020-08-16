package com.hsicen.daggersample

import android.app.Application
import com.hsicen.daggersample.dagger.AppComponent
import com.hsicen.daggersample.dagger.DaggerAppComponent

/**
 * 作者：hsicen  2020/8/16 16:15
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class DaggerApp : Application() {

    lateinit var coreComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        //手动初始化
        coreComponent = DaggerAppComponent.create()
    }
}