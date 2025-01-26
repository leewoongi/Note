package com.woongi.note.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    // 힐트 적용 전까지 의존성 주입
    override fun onCreate() {
        super.onCreate()
    }
}