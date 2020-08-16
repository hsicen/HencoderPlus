package com.android.hsicen.hiltsample.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * 作者：hsicen  2020/8/16 20:58
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：提供全局单例对象  Application作用域
 *
 * ApplicationComponent 全局作用域
 * ActivityComponent
 * FragmentComponent
 * ViewComponent
 * ActivityRetainedComponent
 */

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton //全局单例
    @Provides
    fun provideExecutor(): ExecutorService {
        return ThreadPoolExecutor(
            5, 30, 1, TimeUnit.MINUTES,
            LinkedBlockingDeque(10000)
        )
    }
}