package com.woongi.convention.module

import com.android.build.api.dsl.LibraryExtension
import com.woongi.convention.base.configureComposeProject
import com.woongi.convention.base.configureAndroidProject
import com.woongi.convention.base.configureJunitProject
import com.woongi.convention.base.configureKotlinProject
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class DataConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("kotlin-kapt")
                apply("androidx.room")
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("note.hilt")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinProject(this)
                configureJunitProject(this)
                configureComposeProject(this)

                /** default config 설정을 위해서 libraryExtension 만 사용 */
                configureAndroidProject(this)
            }
        }
    }
}