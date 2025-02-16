plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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
            implementationClass = "com.note.convention.ApplicationConventionPlugin"
        }
    }
}