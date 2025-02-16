plugins {
    id("note.data")
}


android {
    namespace = "com.woongi.data"
}
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
//    kotlinOptions {
//        jvmTarget = "17"
//    }
//
//    room {
//        schemaDirectory("$projectDir/schemas")
//    }
//}
//
//kapt {
//    correctErrorTypes = true
//}
//
//dependencies {
//    implementation(project(":domain"))
//
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//
//    /** 힐트 */
//    implementation(libs.hilt.android)
//    kapt(libs.hilt.compiler)
//
//    /** retrofit */
//    implementation (libs.retrofit)
//    implementation (libs.converter.gson)
//    implementation (libs.logging.interceptor)
//
//    /** room */
//    implementation(libs.androidx.room.runtime)
//    implementation(libs.androidx.room.ktx)
//    testImplementation(libs.androidx.room.testing)
//    implementation(libs.androidx.room.paging)
//    kapt(libs.androidx.room.compiler)
//
//}