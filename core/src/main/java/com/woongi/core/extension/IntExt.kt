package com.woongi.core.extension

import android.content.Context

fun Int.dpToPx(context: Context): Float {
    val density = context.resources.displayMetrics.density
    return this * density
}