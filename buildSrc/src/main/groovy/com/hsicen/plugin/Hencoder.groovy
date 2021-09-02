package com.hsicen.plugin

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class Hencoder implements Plugin<Project> {
    @Override
    void apply(Project project) {
        def extension = project.extensions.create('hsicen', HsicenExtension)

        project.afterEvaluate {//读取配置完成后再执行
            println "Hi $extension.name"
        }

        def baseExtension = project.extensions.getByType(BaseExtension)
        baseExtension.registerTransform(new HencoderTransform())
    }
}
