package com.android.hsicen.hiltsample.hilt

import com.android.hsicen.hiltsample.data.User
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * 作者：hsicen  2020/8/16 19:44
 * 邮箱：codinghuang@163.com
 * 作用：局部依赖图
 * 描述：提供依赖
 */

@Module
@InstallIn(ActivityComponent::class) //限制在Activity内部共享
abstract class HiltModule {

    @Binds //给需要Any的注解提供一个User对象
    abstract fun bindAny(user: User): Any
}
