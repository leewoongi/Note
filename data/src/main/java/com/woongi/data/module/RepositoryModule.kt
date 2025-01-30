package com.woongi.data.module

import com.woongi.data.repository.drawing.DrawingRepositoryImpl
import com.woongi.domain.point.repository.DrawingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSaveRepository(
        drawingRepositoryImpl: DrawingRepositoryImpl
    ): DrawingRepository
}