package com.woongi.note.navigator

import com.woongi.navigator.api.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigatorModule {

    @Binds
    fun provideNavigator(
        impl: NavigatorImpl
    ): Navigator
}