package com.hsicen.a28_plugin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dalvik.system.DexClassLoader
import okio.buffer
import okio.sink
import okio.source
import java.io.File


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            //val utilClass = Utils::class.java
            //val utils = utilClass.newInstance()

            val utilClass = Class.forName("com.hsicen.a28_plugin.utils.Utils")
            val utilConstructor = utilClass.declaredConstructors[0]
            utilConstructor.isAccessible = true
            val utils = utilConstructor.newInstance()
            val shout = utilClass.getDeclaredMethod("shout")
            shout.isAccessible = true

            shout.invoke(utils)
        } catch (e: Exception) {
            println("hsc   反射出错 ${e.printStackTrace()}")
        }

        //复制Assets文件到缓存
        val apk = File("$cacheDir/28_plugin_lib.apk")
        assets.open("apk/28_plugin_lib.apk").source().use {
            apk.sink().buffer().writeAll(it)
        }

        try {
            val classLoader = DexClassLoader(apk.path, cacheDir.path, null, null)
            val pluginUtilsClass = classLoader.loadClass("com.hsicen.a28_plugin_lib.Utils")
            val utilsConstructor = pluginUtilsClass.declaredConstructors[0]
            val utils = utilsConstructor.newInstance()
            val shoutMethod = pluginUtilsClass.getDeclaredMethod("shout")
            shoutMethod.invoke(utils)
        } catch (e: Exception) {
            println("hsc   反射出错 ${e.printStackTrace()}")
        }
    }
}
