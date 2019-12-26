package com.hsicen.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project


class Hencoder implements Plugin<Project> {
    @Override
    void apply(Project project) {
        def extension = project.extensions.create('hsicen', HsicenExtension)

        project.afterEvaluate {
            println "Hi $extension.name"
        }
    }
}
