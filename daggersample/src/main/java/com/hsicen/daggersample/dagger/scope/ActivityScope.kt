package com.hsicen.daggersample.dagger.scope

import javax.inject.Scope

/**
 * 作者：hsicen  2020/8/16 16:31
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：Activity作用域注解
 */

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope
