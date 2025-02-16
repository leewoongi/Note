package com.woongi.convention.module

import com.android.build.api.dsl.LibraryExtension
import com.woongi.convention.base.configureAndroidProject
import com.woongi.convention.base.configureJunitProject
import com.woongi.convention.base.configureKotlinProject
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import java.util.Properties

class DataConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("kotlin-kapt")
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("note.hilt")
                apply("note.room")
            }

            val properties = Properties()
            properties.load(project.rootProject.file("local.properties").inputStream())

            extensions.configure<LibraryExtension> {
                configureKotlinProject(this)
                configureJunitProject(this)

                /** default config 설정을 위해서 libraryExtension 만 사용 */
                configureAndroidProject(this)
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", project(":domain"))

                // Network
                add("implementation", libs.findLibrary("converter-gson").get())
                add("implementation", libs.findLibrary("retrofit").get())
                add("implementation", libs.findLibrary("logging-interceptor").get())
                add("implementation", libs.findLibrary("flow").get())

            }
        }
    }
}