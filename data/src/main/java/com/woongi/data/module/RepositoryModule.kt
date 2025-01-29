package com.woongi.data.module

import com.woongi.data.repository.SaveRepositoryImpl
import com.woongi.domain.point.repository.SaveRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSaveRepository(
        saveRepositoryImpl: SaveRepositoryImpl
    ): SaveRepository
}