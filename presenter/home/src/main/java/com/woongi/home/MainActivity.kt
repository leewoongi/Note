package com.woongi.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("TEST TEST TEST mainActivity")
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyAppScreen()
        }
    }
}