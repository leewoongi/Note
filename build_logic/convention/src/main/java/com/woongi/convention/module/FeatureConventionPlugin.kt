package com.woongi.convention.module

import com.android.build.api.dsl.ComposeOptions
import com.android.build.api.dsl.LibraryExtension
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
                buildFeatures {
                    compose = true
                }

                composeOptions {
                    kotlinCompilerExtensionVersion = "1.5.15"
                }
            }

            extensions.configure<KaptExtension> {
                correctErrorTypes = true
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", project(":domain"))
                add("implementation", project(":core"))
                add("implementation", project(":presenter:detail"))

                val bom = libs.findLibrary("androidx-compose-bom").get()

                add("implementation", platform(bom))
                add("androidTestImplementation", platform(bom))

                add("implementation", libs.findLibrary("androidx-core-ktx").get())
                add("implementation", libs.findLibrary("androidx-appcompat").get())
                add("implementation", libs.findLibrary("androidx-material3").get())
                add("implementation", libs.findLibrary("androidx-foundation").get())
                add("implementation", libs.findLibrary("ui").get())
                add("implementation", libs.findLibrary("ui-tooling-preview").get())
                add("implementation", libs.findLibrary("ui-tooling").get())
                add("implementation", libs.findLibrary("ui-test-manifest").get())
                add("implementation", libs.findLibrary("androidx-hilt-navigation-compose").get())
                add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
                add("implementation", libs.findLibrary("androidx-runtime-livedata").get())
                add("implementation", libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
                add("implementation", libs.findLibrary("androidx-activity-compose").get())
                add("implementation", libs.findLibrary("gson").get())

                add("implementation", libs.findLibrary("junit").get())
                add("implementation", libs.findLibrary("androidx-junit").get())
                add("implementation", libs.findLibrary("androidx-espresso-core").get())
                add("implementation", libs.findLibrary("ui-test-junit4").get())

            }
        }
    }
}
