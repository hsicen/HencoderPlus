package com.android.hsicen.hiltsample.hilt

import com.android.hsicen.hiltsample.data.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

/**
 * 作者：hsicen  2020/8/16 19:55
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：提供具体依赖逻辑
 */

@Module
@InstallIn(ActivityComponent::class)
object HiltModule1 {

    @ActivityScoped
    @Provides //给需要User的注解提供一个User对象
    fun provideUser(): User {
        val user = User()
        user.name = "Miky"
        user.mood = "摸不着头脑"

        return user
    }
}
