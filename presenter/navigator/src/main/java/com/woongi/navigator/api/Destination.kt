package com.woongi.navigator.api

import com.woongi.navigator.NavigateItem

sealed interface Destination {
    data class Main(val item: NavigateItem) : Destination
    data class Detail(val item: NavigateItem) : Destination
}
