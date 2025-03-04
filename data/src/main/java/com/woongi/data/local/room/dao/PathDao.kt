package com.woongi.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.woongi.data.local.room.entity.PathEntity
import com.woongi.data.local.room.entity.relation.PathWithLines
import com.woongi.domain.point.entity.Path

@Dao
interface PathDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(path: PathEntity) : Long

    @Query("SELECT * FROM paths")
    suspend fun getAll(): List<PathEntity>

    @Query("SELECT * FROM paths WHERE id = :pathId")
    suspend fun getPathById(pathId: Long): PathEntity

    @Transaction
    @Query("SELECT * FROM paths")
    fun getPathsWithLines(): List<PathWithLines>

    @Delete
    suspend fun delete(path: PathEntity)
}