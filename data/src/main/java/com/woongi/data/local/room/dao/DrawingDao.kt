package com.woongi.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.woongi.data.local.room.entity.Drawing
import kotlinx.coroutines.flow.Flow

@Dao
interface DrawingDao {

    @Query("SELECT * FROM drawing_table")
    suspend fun getAll(): List<Drawing>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(line: Drawing)
}