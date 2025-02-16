package com.woongi.convention.base

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureApplicationProject(
    applicationExtension: ApplicationExtension
){
    applicationExtension.apply {
        defaultConfig {
            targetSdk = 35

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