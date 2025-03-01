package com.woongi.navigator.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NavigatorModule {

    @Binds
    fun bindsAppNavigator(
        impl: AppNavigatorImpl
    ): AppNavigator
}