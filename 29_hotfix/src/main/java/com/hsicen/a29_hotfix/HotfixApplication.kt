package com.hsicen.a29_hotfix

import android.app.Application
import android.content.Context
import dalvik.system.BaseDexClassLoader
import dalvik.system.PathClassLoader
import java.io.File
import java.lang.invoke.MethodHandle
import java.lang.reflect.Array


/**
 * <p>作者：hsicen  2020/2/9 11:30
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
class HotfixApplication : Application() {

    lateinit var apk: File

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        apk = File("$cacheDir/hotfix.dex")
        if (apk.exists()) {
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

                val oldLength = Array.getLength(dexElementsObject!!)
                val newLength = Array.getLength(newDexElementsObject!!)

                val concatDexElementsObject = Array.newInstance(
                    dexElementsObject.javaClass.componentType!!,
                    oldLength + newLength
                )
                for (i in 0 until newLength) {
                    Array.set(concatDexElementsObject, i, Array.get(newDexElementsObject, i))
                }
                for (i in 0 until oldLength) {
                    Array.set(
                        concatDexElementsObject,
                        newLength + i,
                        Array.get(dexElementsObject, i)
                    )
                }
                dexElementsField.set(pathListObject, concatDexElementsObject)
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
    }
}