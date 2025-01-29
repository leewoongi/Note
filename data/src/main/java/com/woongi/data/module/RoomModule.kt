package com.woongi.data.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase {
//        return Room.databaseBuilder(
//            context,
//            AppDatabase::class.java, "app_database"
//        ).build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideUserDao(database: AppDatabase): UserDao {
//        return database.userDao()
//    }
}