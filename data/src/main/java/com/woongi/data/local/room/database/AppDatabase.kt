package com.woongi.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.woongi.data.local.room.convert.Converts
import com.woongi.data.local.room.dao.DrawingDao
import com.woongi.data.local.room.entity.Drawing

@Database(
    entities = [Drawing::class],
    version = 1
)
@TypeConverters(
    value = [
        Converts::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun drawingDao(): DrawingDao
}