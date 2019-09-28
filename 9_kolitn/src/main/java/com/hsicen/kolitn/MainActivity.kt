package com.hsicen.kolitn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

/**
 * <p>作者：Hsicen  2019/9/21 17:56
 * <p>邮箱：codinghuang@163.com
 * <p>功能：
 * <p>描述：kotlin的简单实用
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // testGlobalScope()
        testCoroutineScope()
    }

    private fun testCoroutineScope() {
        val cs = CoroutineScope(Dispatchers.IO)
        val launch = cs.launch {
            println("3开始了")
            println("3当前线程：${Thread.currentThread().name}")
            Thread.sleep(500)
            println("3结束了")
        }

        println("3会阻塞么？")
        launch.start()
        Thread.sleep(50)
        launch.cancel()
        println("3取消了")
    }

    private fun testGlobalScope() {
        val scope = GlobalScope.launch {
            println("2当前线程：${Thread.currentThread().name}")
            println("2开始了")
            Thread.sleep(100)
            println("2结束了")
        }
        println("2会阻塞么？")
        scope.start()
        Thread.sleep(50)
        scope.cancel()
        println("2取消了")
    }

    suspend fun getImage(id: Int) = withContext(Dispatchers.IO) {
        // 从网络获取图片资源
    }
}
