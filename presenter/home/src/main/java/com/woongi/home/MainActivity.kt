package com.woongi.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.woongi.core.extension.parcelable
import com.woongi.navigator.NavigateItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navigateItem: NavigateItem? = intent.parcelable("data")
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyAppScreen(navigateItem)
        }
    }
}