package com.hsicen.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import groovy.io.FileType

/**
 * 作者：hsicen  2019/12/30 8:30
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class HencoderTransform extends Transform {

  @Override
  String getName() {
    return 'hencoderTransform'
  }

  @Override
  Set<QualifiedContent.ContentType> getInputTypes() {
    return TransformManager.CONTENT_CLASS
  }

  @Override
  Set<? super QualifiedContent.Scope> getScopes() {
    return TransformManager.SCOPE_FULL_PROJECT
  }

  @Override
  boolean isIncremental() {
    return false
  }

  @Override
  void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
    def srcInputs = transformInvocation.inputs //a compose of jar list and directory list
    def destOutputs = transformInvocation.outputProvider

    srcInputs.each { TransformInput input ->

      //处理Directory
      input.directoryInputs.each { DirectoryInput dirInput ->
        def dirFile = dirInput.file
        if (dirFile) {
          //过滤出.class 文件(从classes文件夹中过滤出 .class 文件)
          dirFile.traverse(type: FileType.FILES, nameFilter: ~/.*\.class/) {
            //使用字节码工具对class文件进行处理
            println "Find directory: $dirFile.name ; path: $dirFile.path"
          }
        }

        //复制文件到指定目录
        def dirDest = destOutputs.getContentLocation(dirInput.name, dirInput.contentTypes, dirInput.scopes, Format.DIRECTORY)
        FileUtils.copyDirectory(dirFile, dirDest)
      }

      //处理Jar包
      input.jarInputs.each { JarInput jarInput ->
        def jarFile = jarInput.file
        println "Find jar: $jarFile.name ; path: $jarFile.path"

        def jarDest = destOutputs.getContentLocation(jarInput.name, jarInput.contentTypes, jarInput.scopes, Format.JAR)
        FileUtils.copyFile(jarFile, jarDest)
      }
    }
  }
}
