package com.woongi.convention.base

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project

internal fun Project.configureApplicationProject(
    applicationExtension: ApplicationExtension
){
    applicationExtension.apply {
        defaultConfig {
            targetSdk = 35
            lint.targetSdk = 35
            compileSdk = 35
            minSdk = 24
            namespace = "com.woongi.note"
            applicationId = "com.woongi.note"
            versionCode = 20250215
            versionName = "1.0.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

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
        }
    }
}