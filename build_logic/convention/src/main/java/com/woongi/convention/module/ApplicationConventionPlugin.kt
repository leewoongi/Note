package com.woongi.convention.module

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import com.woongi.convention.base.configureApplicationProject
import com.woongi.convention.base.configureKotlinProject
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import java.util.Properties

class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            pluginManager.apply {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("kotlin-kapt")
                apply("note.hilt")
            }

            val properties = Properties()
            properties.load(project.rootProject.file("local.properties").inputStream())

            extensions.configure<ApplicationExtension> {
                configureApplicationProject(this)
                configureKotlinProject(this)

                defaultConfig {
                    namespace = "com.woongi.note"
                    applicationId = "com.woongi.note"
                    versionCode = 20250215
                    versionName = "1.0.0"
//
//                    targetSdk = 35
//
//                    buildFeatures {
//                        // gradle 8.0부터 buildConfig를 사용하기 위함
//                        buildConfig = true
//                    }
//
//                    buildTypes {
//                        release {
//                            isMinifyEnabled = false
//                            proguardFiles(
//                                getDefaultProguardFile("proguard-android-optimize.txt"),
//                                "proguard-rules.pro"
//                            )
//                        }
//                    }
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", project(":domain"))
                add("implementation", project(":data"))
                add("implementation", project(":presenter:home"))
                add("implementation", project(":core"))

                add("implementation", libs.findLibrary("junit").get())
                add("implementation", libs.findLibrary("androidx-junit").get())
                add("implementation", libs.findLibrary("androidx-espresso-core").get())

            }
        }
    }
}