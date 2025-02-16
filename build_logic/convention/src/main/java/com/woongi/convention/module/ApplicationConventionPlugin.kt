package com.woongi.convention.module

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ComposeOptions
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.support.kotlinCompilerOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import java.util.Properties

class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            pluginManager.apply {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("kotlin-kapt")
                // 힐트 추가해야함
            }

            val properties = Properties()
            properties.load(project.rootProject.file("local.properties").inputStream())

            extensions.configure<ApplicationExtension> {
                compileSdk = 35
                namespace = "com.woongi.note"

                defaultConfig {
                    applicationId = "com.woongi.note"
                    versionCode = 20250215
                    versionName = "1.0.0"

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    targetSdk = 35
                    minSdk = 24

                    buildFeatures {
                        // gradle 8.0부터 buildConfig를 사용하기 위함
                        buildConfig = true
                    }

                    buildTypes {
                        release {
                            isMinifyEnabled = false
                            proguardFiles(
                                getDefaultProguardFile("proguard-android-optimize.txt"),
                                "proguard-rules.pro"
                            )
                        }
                    }

                    compileOptions {
                        sourceCompatibility = JavaVersion.VERSION_17
                        targetCompatibility = JavaVersion.VERSION_17
                    }

                    extensions.configure<KotlinJvmCompilerOptions> {
                        JavaVersion.VERSION_17.toString()
                    }
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", project(":domain"))
                add("implementation", project(":data"))
                add("implementation", project(":presenter"))
                add("implementation", project(":core"))

                add("implementation", libs.findLibrary("junit").get())
                add("implementation", libs.findLibrary("androidx-junit").get())
                add("implementation", libs.findLibrary("androidx-espresso-core").get())

            }
        }
    }
}