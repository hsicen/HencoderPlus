package com.hsicen.daggersample.dagger

import com.hsicen.daggersample.MainActivity
import com.hsicen.daggersample.dagger.scope.ActivityScope
import com.hsicen.daggersample.view.UserView
import dagger.Component

/**
 * 作者：hsicen  2020/8/16 16:29
 * 邮箱：codinghuang@163.com
 * 作用：创建编织图
 * 描述：提供依赖注入方法  负责编织依赖图
 */

@ActivityScope
@Component(modules = [AppModule::class])
interface AppComponent {

  fun inject(act: MainActivity)

  fun inject(view: UserView)
}