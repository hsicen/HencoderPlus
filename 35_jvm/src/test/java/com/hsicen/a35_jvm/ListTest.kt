package com.hsicen.a35_jvm

import org.junit.Assert
import org.junit.Test
import java.util.*

/**
 * 作者：hsicen  2020/5/11 10:59
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class ListTest {

    @Test
    fun linkedList() {
        val mLinkedList = LinkedList<Int>()
        mLinkedList.add(23)
        mLinkedList.add(23)
        mLinkedList.add(23)
        mLinkedList.add(23)

        val indexTwo = mLinkedList[2]

        Assert.assertEquals(1, mLinkedList.size)
    }
}