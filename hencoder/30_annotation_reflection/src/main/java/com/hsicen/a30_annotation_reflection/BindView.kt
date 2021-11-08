package com.hsicen.a30_annotation_reflection

/**
 * 作者：hsicen  2020/2/9 15:35
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：注解
 */

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class BindView(val value: Int)
