package com.woongi.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.woongi.data.local.room.convert.Converts
import com.woongi.data.local.room.dao.LineDao
import com.woongi.data.local.room.dao.PathDao
import com.woongi.data.local.room.dao.PointDao
import com.woongi.data.local.room.entity.LineEntity
import com.woongi.data.local.room.entity.PathEntity
import com.woongi.data.local.room.entity.PointEntity

@Database(
    entities = [
        PathEntity::class,
        LineEntity::class,
        PointEntity::class
    ],
    version = 3
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun pathDao(): PathDao
    abstract fun lineDao(): LineDao
    abstract fun pointDao(): PointDao
}