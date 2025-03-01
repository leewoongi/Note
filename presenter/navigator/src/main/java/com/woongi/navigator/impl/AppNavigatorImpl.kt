package com.woongi.navigator.impl

import android.content.Intent
import com.woongi.navigator.api.AppNavigator
import com.woongi.navigator.api.Destination
import com.woongi.navigator.api.Navigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNavigatorImpl
@Inject constructor(
    private val navigator: Navigator
): AppNavigator {
    override fun createIntent(destination: Destination): Intent {
        return when(destination) {
            is Destination.Main -> navigator.createIntent(destination)
        }
    }
}