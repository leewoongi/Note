package com.woongi.data.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.woongi.data.local.room.convert.Converts
import com.woongi.data.local.room.dao.LineDao
import com.woongi.data.local.room.dao.PathDao
import com.woongi.data.local.room.dao.PointDao
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
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "app_database"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // 외래 키 제약 조건 활성화
                db.execSQL("PRAGMA foreign_keys=ON;")
            }
        }).fallbackToDestructiveMigration().build()
//            .addMigrations(migration_1_2)
    }

    @Provides
    @Singleton
    fun providePathDao(database: AppDatabase): PathDao {
        return database.pathDao()
    }

    @Provides
    @Singleton
    fun provideLineDao(database: AppDatabase): LineDao {
        return database.lineDao()
    }

    @Provides
    @Singleton
    fun providePointDao(database: AppDatabase): PointDao {
        return database.pointDao()
    }
}