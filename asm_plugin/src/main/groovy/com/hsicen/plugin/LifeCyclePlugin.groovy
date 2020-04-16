package com.hsicen.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

public class LifeCyclePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        System.out.println("=====Hello World !!! This is a asm plugin =====")
    }
}