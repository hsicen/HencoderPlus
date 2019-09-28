package com.hsicen.kolitn

/**
 * <p>作者：Hsicen  2019/9/23 9:47
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：where 上界中只能有一个为具体类，其它必须为接口类
 */
class Monsters<T> where T : Animal, T : Food, T : Person {

}