package com.hsicen.kolitn

/**
 * <p>作者：hsicen  2019/11/22 9:22
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：枚举类型定义
 */
enum class Food(val no: Int) {
    APPLE(1), BANANA(2), WATERMELON(3);

    fun getFoodSize() = 4
}
