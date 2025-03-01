package com.woongi.navigator.api

import android.content.Intent

interface Navigator {
    fun createIntent(destination: Destination)
}