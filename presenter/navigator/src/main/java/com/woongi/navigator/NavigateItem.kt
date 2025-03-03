package com.woongi.navigator

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class NavigateItem(
    val item: @RawValue Any? = null
) : Parcelable