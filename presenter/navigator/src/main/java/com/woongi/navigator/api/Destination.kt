package com.woongi.navigator.api

sealed interface Destination {
    data object Main : Destination
    data object Detail : Destination
}
