package com.woongi.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.woongi.data.local.room.entity.LineEntity
import com.woongi.data.local.room.entity.PointEntity

@Dao
interface PointDao {
    @Insert
    suspend fun insert(point: PointEntity)

    @Update
    suspend fun updatePoint(point: PointEntity)

    @Query("SELECT * FROM points WHERE lineId = :lineId")
    suspend fun getPointsByLineId(lineId: Int): List<PointEntity>
}