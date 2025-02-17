package com.woongi.convention.base

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project

internal fun Project.configureAndroidProject(
    libraryExtension: LibraryExtension
){
    libraryExtension.apply {
        defaultConfig {
            lint.targetSdk = 35
            compileSdk = 35

            minSdk = 24
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            buildFeatures {
                // gradle 8.0부터 buildConfig를 사용하기 위함
                buildConfig = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = "1.5.15"
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
        }
    }
}