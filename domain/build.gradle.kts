plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    /** 힐트 */
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.core)
    implementation(libs.flow)

}
