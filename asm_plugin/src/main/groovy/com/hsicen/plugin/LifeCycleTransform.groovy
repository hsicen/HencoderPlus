package com.hsicen.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import groovy.io.FileType

public class LifeCycleTransform extends Transform {

    @Override
    String getName() {
        //gradle 任务名字
        return "LifeCycleTransform"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        //处理文件类型
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        //处理文件范围
        return TransformManager.PROJECT_ONLY
    }

    @Override
    boolean isIncremental() {
        //是否支持增量编译
        return false
    }

    @Override
    //具体的字节码处理逻辑
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        //transformInvocation 可以获取到输入流和输出目录
        def inputs = transformInvocation.inputs   //获取到输入流(分为jar包和directory两种)
        inputs.each { TransformInput transformInput ->
            //获取到directory的输入流，表示以源码方式参与项目编译的所有目录结构及其目录下的文件(手写的类)
            transformInput.directoryInputs.each { DirectoryInput directoryInput ->
                def dirFile = directoryInput.file
                if (dirFile) {
                    dirFile.traverse(type: FileType.FILES, nameFilter: ~/.*\.class/) { File file ->
                        System.out.println("Find class: " + file.name + "   Path：" + file.path)
                    }
                }
            }
        }
    }
}
