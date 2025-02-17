package com.woongi.convention.base

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureJunitProject(
    commonExtension : CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        dependencies {
            add("implementation", libs.findLibrary("junit").get())
            add("implementation", libs.findLibrary("androidx-junit").get())
            add("implementation", libs.findLibrary("androidx-espresso-core").get())
        }
    }
}