package com.woongi.navigator

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NavigateItem<T : Parcelable>(
    val item: T? = null
) : Parcelable