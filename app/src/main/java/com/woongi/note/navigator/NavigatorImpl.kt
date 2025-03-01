package com.woongi.note.navigator

import android.content.Context
import android.content.Intent
import com.woongi.detail.DetailActivity
import com.woongi.home.MainActivity
import com.woongi.navigator.api.Destination
import com.woongi.navigator.api.Navigator
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NavigatorImpl
@Inject constructor(
    @ApplicationContext private val context: Context
): Navigator{
    override fun createIntent(destination: Destination) {
        val intent = when(destination) {
            Destination.Main -> Intent(context, MainActivity::class.java)
            Destination.Detail -> Intent(context, DetailActivity::class.java)
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}