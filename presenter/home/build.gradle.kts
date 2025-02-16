plugins {
    id("note.feature")
}

android {
    namespace = "com.woongi.home"
    compileSdk = 35
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

//plugins {
//    alias(libs.plugins.android.library)
//    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.hilt.android)
//    alias(libs.plugins.kotlin.compose)
//    id("kotlin-kapt")
//}
//
//android {
//    namespace = "com.woongi.home"
//    compileSdk = 35
//
//    defaultConfig {
//        minSdk = 24
//
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles("consumer-rules.pro")
//    }
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_17
//        targetCompatibility = JavaVersion.VERSION_17
//    }
//
//    buildFeatures {
//        compose = true
//    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.5.15" // Kotlin 2.0과 호환
//    }
//
//    kotlinOptions {
//        jvmTarget = "17"
//    }
//}
//
//kapt {
//    correctErrorTypes = true
//}
//
//
//dependencies {
//    implementation(project(":core"))
//    implementation(project(":domain"))
//    implementation(project(":presenter:detail"))
//
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//    testImplementation(libs.junit) // 추가해야함
//    androidTestImplementation(libs.androidx.junit) // 추가해야함
//    androidTestImplementation(libs.androidx.espresso.core) // 추가해야함
//
//    /** 컴포즈 */
//    implementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(platform(libs.androidx.compose.bom))
//    implementation(libs.material3)
//    implementation(libs.androidx.foundation)
//    implementation(libs.ui)
//    implementation(libs.ui.tooling.preview)
//    debugImplementation(libs.ui.tooling)
//    androidTestImplementation(libs.ui.test.junit4) // 추가해야함
//    debugImplementation(libs.ui.test.manifest)
//    implementation(libs.androidx.lifecycle.viewmodel.compose)
//    implementation(libs.androidx.runtime.livedata)
//    implementation(libs.androidx.activity.compose)
//
//
//    /** 힐트 */
//    implementation(libs.hilt.android)
//    kapt(libs.hilt.compiler)
//    implementation(libs.androidx.hilt.navigation.compose)
//
//    /**gson*/
//    implementation(libs.gson)
//}