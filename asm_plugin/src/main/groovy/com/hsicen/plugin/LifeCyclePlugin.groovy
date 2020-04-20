package com.hsicen.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

public class LifeCyclePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        System.out.println("=====Hello World !!! This is a asm plugin =====")
        def appExtension = project.extensions.getByType(AppExtension)
        System.out.println("===== register auto track transform =====")
        def transform = new LifeCycleTransform()
        appExtension.registerTransform(transform)
    }
}