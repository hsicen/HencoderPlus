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

        showTitleBt.setOnClickListener {
            titleTv.text = Title().getTitle()
        }

        //从网络下载文件保存到缓存目录
        hotfixBt.setOnClickListener {
            //loadHotfixDex()
        }

        removeHotfixBt.setOnClickListener {
            //删除文件
        }

        killSelfBt.setOnClickListener {
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }

    /*** 全量更新，加载整个apk文件*/
    private fun loadHotfixDex() {
        try {//复制apk文件(生产环境是从网络下载dex文件)
            val apk = File("$cacheDir/29_hotfix.apk")
            assets.open("apk/hotfix.apk").source().use {
                apk.sink().buffer().writeAll(it)
            }

            try {
                val loaderClass = BaseDexClassLoader::class.java
                val pathListField = loaderClass.getDeclaredField("pathList")
                pathListField.isAccessible = true

                val pathListObject = pathListField.get(classLoader) // getClassLoader().pathList
                val pathListClass = pathListObject.javaClass
                val dexElementsField = pathListClass.getDeclaredField("dexElements")
                dexElementsField.isAccessible = true

                val dexElementsObject = dexElementsField.get(pathListObject)
                val newClassLoader = PathClassLoader(apk.path, null)
                val newPathListObject = pathListField.get(newClassLoader) // newClassLoader.pathList
                val newDexElementsObject =
                    dexElementsField.get(newPathListObject) // newClassLoader.pathList.dexElements

                dexElementsField.set(pathListObject, newDexElementsObject)
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        } catch (e: Exception) {
            Log.d("hsc ", "替换出错  ${e.printStackTrace()}")
        }
    }
}
