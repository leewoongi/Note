package com.woongi.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.woongi.data.local.room.dao.DrawingDao
import com.woongi.data.local.room.entity.Drawing


@Database(
    entities = [Drawing::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun drawingDao(): DrawingDao
}