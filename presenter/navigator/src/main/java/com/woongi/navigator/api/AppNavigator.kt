package com.woongi.navigator.api

import android.content.Intent

interface AppNavigator {
    fun createIntent(destination: Destination): Intent
}