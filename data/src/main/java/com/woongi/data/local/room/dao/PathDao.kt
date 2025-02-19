package com.woongi.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.woongi.data.local.room.entity.PathEntity
import com.woongi.data.local.room.entity.relation.PathWithLines

@Dao
interface PathDao {
    @Insert
    suspend fun insert(path: PathEntity) : Long

    @Query("SELECT * FROM paths")
    suspend fun getAll(): List<PathEntity>

    @Query("SELECT * FROM paths WHERE id = :pathId")
    suspend fun getPathById(pathId: Long): PathEntity

    @Transaction
    @Query("SELECT * FROM paths")
    fun getPathsWithLines(): List<PathWithLines>
}