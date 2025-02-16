package com.woongi.convention.module

import com.android.build.api.dsl.ComposeOptions
import com.android.build.api.dsl.LibraryExtension
import com.woongi.convention.base.configureComposeProject
import com.woongi.convention.base.configureFeatureProject
import com.woongi.convention.base.configureJunitProject
import com.woongi.convention.base.configureKotlinProject
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            pluginManager.apply {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("kotlin-kapt")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("note.hilt")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinProject(this)
                configureJunitProject(this)
                configureComposeProject(this)

                /** default config 설정을 위해서 libraryExtension 만 사용 */
                configureFeatureProject(this)
            }

            extensions.configure<KaptExtension> {
                correctErrorTypes = true
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", project(":domain"))
                add("implementation", project(":core"))
                add("implementation", libs.findLibrary("gson").get())
            }
        }
    }
}
