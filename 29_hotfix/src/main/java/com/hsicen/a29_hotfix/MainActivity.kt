package com.hsicen.a29_hotfix

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dalvik.system.BaseDexClassLoader
import dalvik.system.PathClassLoader
import kotlinx.android.synthetic.main.activity_main.*
import okio.buffer
import okio.sink
import okio.source
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_load.setOnClickListener {
            tv_title.text = Title().getTitle()
        }

        btn_hotfix.setOnClickListener {
            loadHotfixDex()
        }
    }

    /*** 全量更新，加载整个apk文件*/
    private fun loadHotfixDex() {
        try {//复制apk文件(生产环境是从网络下载dex文件)
            val apk = File("$cacheDir/29_hotfix.apk")
            assets.open("apk/hotfix.apk").source().use {
                apk.sink().buffer().writeAll(it)
            }

            //dexElements 替换
            val loaderClass = BaseDexClassLoader::class.java
            val pathListFiled = loaderClass.getDeclaredField("pathList")
            pathListFiled.isAccessible = true
            val pathListObject = pathListFiled.get(classLoader)

            val pathListClass = pathListObject::class.java
            val dexElementFiled = pathListClass.getDeclaredField("dexElements")
            dexElementFiled.isAccessible = true

            val newClassLoader = PathClassLoader(apk.path, null)
            val newPathListObject = pathListFiled.get(newClassLoader)
            val newDexElementObject = dexElementFiled.get(newPathListObject)

            //替换
            dexElementFiled.set(pathListObject, newDexElementObject)
        } catch (e: Exception) {
            Log.d("hsc ", "替换出错  ${e.printStackTrace()}")
        }
    }
}
