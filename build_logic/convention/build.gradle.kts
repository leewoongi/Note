plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.androidx.room.gradle.plugin)
    compileOnly(libs.android.pluginGradle)
    compileOnly(libs.kotlin.pluginGradle)
}

gradlePlugin {
    plugins {
        register("AndroidApplication") {
            id = "note.application"
            implementationClass = "com.woongi.convention.module.ApplicationConventionPlugin"
        }

        register("AndroidFeature") {
            id = "note.feature"
            implementationClass = "com.woongi.convention.module.FeatureConventionPlugin"
        }

        register("AndroidDomain") {
            id = "note.domain"
            implementationClass = "com.woongi.convention.module.DomainConventionPlugin"
        }

        register("AndroidHilt") {
            id = "note.hilt"
            implementationClass = "com.woongi.convention.HiltConventionPlugin"
        }
    }
}