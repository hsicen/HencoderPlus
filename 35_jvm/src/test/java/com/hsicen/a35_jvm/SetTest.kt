package com.hsicen.a35_jvm

import org.junit.Test
import java.util.*
import kotlin.collections.HashSet
import kotlin.collections.forEachIndexed

/**
 * 作者：hsicen  2020/5/11 11:23
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class SetTest {

    @Test
    fun hashSet() {

        val mHashSet = HashSet<User>()
        mHashSet.add(User("1", 1))  //0
        mHashSet.add(User("34", 34)) //2
        mHashSet.add(User("12", 12)) //5
        mHashSet.add(User("33", 33)) //3
        mHashSet.add(User("49", 49)) //6
        mHashSet.add(User("33", 33)) //3
        mHashSet.add(User("23", 23)) //4
        mHashSet.add(User("3", 3)) //1

        mHashSet.forEachIndexed { index, user ->
            println("第$index   $user")
        }
    }

    @Test
    fun linkedHashSet() {
        val mHashSet = LinkedHashSet<User>()
        mHashSet.add(User("1", 1))  //0
        mHashSet.add(User("34", 34)) //2
        mHashSet.add(User("12", 12)) //5
        mHashSet.add(User("33", 33)) //3
        mHashSet.add(User("49", 49)) //6
        mHashSet.add(User("33", 33)) //3
        mHashSet.add(User("23", 23)) //4
        mHashSet.add(User("3", 3)) //1

        mHashSet.forEachIndexed { index, user ->
            println("第$index   $user")
        }
    }


    @Test
    fun treeSet() {
        val mTreeSet = TreeSet<User>()
        mTreeSet.add(User("1", 1))  //0
        mTreeSet.add(User("34", 34)) //2
        mTreeSet.add(User("12", 12)) //5
        mTreeSet.add(User("33", 33)) //3
        mTreeSet.add(User("49", 49)) //6
        mTreeSet.add(User("33", 33)) //3
        mTreeSet.add(User("23", 23)) //4
        mTreeSet.add(User("3", 3)) //1

        mTreeSet.forEachIndexed { index, user ->
            println("第$index   $user")
        }
    }
}