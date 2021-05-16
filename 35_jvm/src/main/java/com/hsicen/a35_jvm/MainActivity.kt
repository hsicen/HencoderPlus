package com.hsicen.a35_jvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a35_jvm.databinding.ActivityMainBinding
import java.lang.ref.SoftReference
import java.lang.ref.WeakReference
import java.util.*

/**
 * 作者：hsicen  2020/4/20 17:57
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：AppClassLoader，ExtClassLoader，ClassLoader
 * BaseDexClassLoader，PathClassLoader，DexClassLoader
 */
class MainActivity : AppCompatActivity() {
    private lateinit var mBind: ActivityMainBinding

    private val referent = User("", 12)
    private var weakUser = WeakReference<User>(referent)
    private var softUser = SoftReference<User>(referent)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBind.root)

        val bytChar = CharArray(233333333)

        val mClassLoader = MainActivity::class.java.classLoader
        println("MainActivity:  $mClassLoader")
        Int.MAX_VALUE

        mBind.btnGc.setOnClickListener {
            println("hsc  Before: weakUser -> $weakUser   softUser -> $softUser")
            System.gc()
            println("hsc  After: weakUser -> $weakUser   softUser -> $softUser")
        }

        val mLinkedList = LinkedList<Int>()
        mLinkedList.add(23)
        mLinkedList.add(23)
        mLinkedList.add(23)
    }
}
