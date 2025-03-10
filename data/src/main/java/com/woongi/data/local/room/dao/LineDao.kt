package com.woongi.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.woongi.data.local.room.entity.LineEntity
import com.woongi.data.local.room.entity.PathEntity
import com.woongi.data.local.room.entity.relation.LineWithPoints
import com.woongi.data.local.room.entity.relation.PathWithLines

@Dao
interface LineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(line: LineEntity) : Long

    @Query("SELECT * FROM lines WHERE pathId = :pathId")
    suspend fun getLinesByPathId(pathId: Int): List<LineEntity>

    @Transaction
    @Query("SELECT * FROM lines")
    suspend fun getLinesWithPoints(): List<LineWithPoints>
}