package com.hsicen.recyclerviewcore

import org.junit.Test

/**
 * 作者：hsicen  2020/3/27 17:55
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class RangeTest {

    @Test
    fun downToLoop() {
        val bottomVisiblePosition = 4
        for (i in (bottomVisiblePosition-1).downTo(0)) {
            println("result:  $i")
        }
    }
}