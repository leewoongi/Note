package com.woongi.convention.base

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureComposeProject(
    commonExtension : CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        with(buildFeatures) {
            compose = true
        }
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    dependencies {
        val bom = libs.findLibrary("androidx-compose-bom").get()

        add("implementation", platform(bom))
        add("androidTestImplementation", platform(bom))

        add("implementation", libs.findLibrary("androidx-core-ktx").get())
        add("implementation", libs.findLibrary("androidx-appcompat").get())
        add("implementation", libs.findLibrary("material").get())
        add("implementation", libs.findLibrary("material3").get())
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
    }
}