package com.woongi.home.model.constants

sealed class NavigationEvent {
    data object HOME : NavigationEvent()
    data object DETAIL : NavigationEvent()
}