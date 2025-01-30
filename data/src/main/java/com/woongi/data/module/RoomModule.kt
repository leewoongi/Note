package com.woongi.data.module

import android.content.Context
import androidx.room.Room
import com.woongi.data.local.room.dao.DrawingDao
import com.woongi.data.local.room.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDrawingDao(database: AppDatabase): DrawingDao {
        return database.drawingDao()
    }
}