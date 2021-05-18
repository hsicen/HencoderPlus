package com.hsicen.daggersample.dagger

import com.hsicen.daggersample.dagger.scope.ActivityScope
import dagger.Module
import dagger.Provides
import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * 作者：hsicen  2020/8/16 22:16
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

@Module
object AppModule {

  @ActivityScope
  @Provides
  fun provideExecutor(): ExecutorService {
    return ThreadPoolExecutor(
      5, 30, 1, TimeUnit.MINUTES,
      LinkedBlockingDeque(10000)
    )
  }
}