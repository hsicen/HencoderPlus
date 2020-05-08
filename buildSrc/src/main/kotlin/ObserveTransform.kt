package com.hsicen.plugin

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils

/**
 * 作者：hsicen  2020/5/7 14:19
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class ObserveTransform : Transform() {
    override fun getName(): String {
        return "ObserveTransform"
    }

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {

        return TransformManager.CONTENT_CLASS
    }

    override fun isIncremental(): Boolean {
        return false
    }

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return TransformManager.PROJECT_ONLY
    }

    override fun transform(transformInvocation: TransformInvocation?) {

        val transformInputs = transformInvocation?.inputs ?: return
        val outputProvider = transformInvocation.outputProvider
        println("开始ObserverTransform")

        transformInputs.forEach { transformInput ->

            //原封不动的复制到目的地
            transformInput.directoryInputs.forEach { directoryInput ->
                val dest = outputProvider.getContentLocation(
                    directoryInput.name,
                    directoryInput.contentTypes,
                    directoryInput.scopes,
                    Format.DIRECTORY
                )

                FileUtils.copyDirectory(directoryInput.file, dest)
            }

            transformInput.jarInputs.forEach { jarInput ->
                val dest = outputProvider.getContentLocation(
                    jarInput.name,
                    jarInput.contentTypes,
                    jarInput.scopes,
                    Format.JAR
                )

                FileUtils.copyFile(jarInput.file, dest)
            }
        }
    }

}