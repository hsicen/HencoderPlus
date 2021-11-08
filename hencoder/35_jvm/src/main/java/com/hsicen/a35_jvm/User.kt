package com.hsicen.a35_jvm

/**
 * 作者：hsicen  2020/5/9 15:57
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
data class User(val name: String, val age: Int) : Comparable<User> {

    init {
        println("当前HashCode： ${hash(this)}   位置：${(16 - 1) and hash(this)}")
    }

    /* ---------------- Static utilities -------------- */
    private fun hash(key: Any?): Int {
        var h: Int
        return if (key == null) 0 else key.hashCode().also { h = it } xor (h ushr 16)
    }

    override fun compareTo(other: User): Int {

        return this.age - other.age
    }
}
