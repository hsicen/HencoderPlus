package com.hsicen.a35_jvm

import org.junit.Test
import java.util.*

/**
 * 作者：hsicen  2020/5/11 15:23
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class MapTest {

    @Test
    fun hashMap() {
        val mHashMap = HashMap<Int, User>()
        mHashMap.put(22, User("23", 23))

        println("数组大小：${tableSizeFor(16)}")
        println("数组大小：${tableSizeFor(17)}")
        println("数组大小：${tableSizeFor(21)}")
        println("数组大小：${tableSizeFor(15)}")

        mHashMap.put(2, User("2", 2))
        mHashMap.put(22, User("23", 23))
        mHashMap.put(44, User("44", 44))
        mHashMap.put(44, User("45", 45))
        mHashMap.put(33, User("33", 32))
        mHashMap.put(33, User("33", 55))
        mHashMap.put(44, User("46", 99))

        mHashMap.forEach { t, u ->
            println("key：$t  value：$u")
        }
    }

    private fun tableSizeFor(cap: Int): Int {
        var n = cap - 1
        n = n or (n ushr 1)
        n = n or (n ushr 2)
        n = n or (n ushr 4)
        n = n or (n ushr 8)
        n = n or (n ushr 16)
        return if (n < 0) 1 else if (n >= 1 shl 30) (1 shl 30) else n + 1
    }

    @Test
    fun linkedHashMap() {
        val mLinkedHashMap = LinkedHashMap<Int, User>()
        mLinkedHashMap.put(1, User("1", 1))
    }

    @Test
    fun treeMap() {
        val mTreeMap = TreeMap<Int, User>()
        mTreeMap.put(1, User("1", 1))
        mTreeMap.put(44, User("44", 44))
        mTreeMap.put(25, User("25", 25))
        mTreeMap.put(1, User("1", 1))
        mTreeMap.put(17, User("17", 17))

        mTreeMap.forEach {

            println("key：${it.key}  value：${it.value}")
        }
    }

    @Test
    fun hashTable() {
        val mHashtable = Hashtable<Int, User>()
        mHashtable.put(1, User("1", 1))
        mHashtable.put(5, User("5", 5))
        mHashtable.put(77, User("77", 77))
        mHashtable.put(16, User("16", 16))
        mHashtable.put(41, User("41", 41))
        mHashtable.put(1, User("1", 1))
        mHashtable.put(53, User("53", 53))
        mHashtable.put(16, User("16", 18))


        mHashtable.forEach { t, u ->
            println("key：$t  value：$u")
        }
    }
}