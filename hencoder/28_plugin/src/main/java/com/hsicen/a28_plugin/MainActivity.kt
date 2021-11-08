package com.hsicen.a28_plugin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a28_plugin.databinding.ActivityMainBinding
import dalvik.system.PathClassLoader
import okio.buffer
import okio.sink
import okio.source
import java.io.File


class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnLoad.setOnClickListener {
            //复制Assets文件到缓存
            val apk = File("$cacheDir/28_plugin_lib.apk")
            assets.open("apk/28_plugin_lib.apk").source().use {
                apk.sink().buffer().writeAll(it)
            }

            try {
                val classLoader = PathClassLoader(apk.path, null)
                val pluginUtilsClass = classLoader.loadClass("com.hsicen.a28_plugin_lib/Utils/")
                val utilsConstructor = pluginUtilsClass.declaredConstructors[0]
                utilsConstructor.isAccessible = true
                val utils = utilsConstructor.newInstance()
                val shoutMethod = pluginUtilsClass.getDeclaredMethod("shout")
                shoutMethod.isAccessible = true
                shoutMethod.invoke(utils)
            } catch (e: Exception) {
                println("hsc   反射出错 ${e.printStackTrace()}")
            }
        }

        mBinding.btnInvoke.setOnClickListener {
            try {
                //val utilClass = Utils::class.java
                //val utils = utilClass.newInstance()

                val utilClass = Class.forName("Utils")
              val utilConstructor = utilClass.declaredConstructors[0]
                utilConstructor.isAccessible = true
                val utils = utilConstructor.newInstance()
                val shout = utilClass.getDeclaredMethod("shout")
                shout.isAccessible = true

                shout.invoke(utils)
            } catch (e: Exception) {
                println("hsc   反射出错 ${e.printStackTrace()}")
            }
        }

        mBinding.btnFinish.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("hsc", "应用Destroy")
    }

    override fun finish() {
        super.finish()
        Log.d("hsc", "应用finish")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("hsc", "应用onActivityResult")
    }
}
