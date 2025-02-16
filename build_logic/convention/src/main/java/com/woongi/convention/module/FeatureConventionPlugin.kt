package com.woongi.convention.module

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.konan.properties.Properties

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            pluginManager.apply {

            }
        }
    }
}



//val bom = libs.findLibrary("androidx-compose-bom").get()
//
//add("implementation", platform(bom))
//add("androidTestImplementation", platform(bom))
//
//add("implementation", libs.findLibrary("androidx-foundation").get())
//add("implementation", libs.findLibrary("androidx-hilt-navigation-compose").get())
//add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
//add("implementation", libs.findLibrary("androidx-runtime-livedata").get())
//add("implementation", libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
//add("implementation", libs.findLibrary("androidx-activity-compose").get())
//add("implementation", libs.findLibrary("androidx-appcompat").get())
//add("implementation", libs.findLibrary("androidx-material3").get())
