package com.woongi.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 경로 데이터 -
 * 선이 여러개 모인거
 */
@Entity(tableName = "paths")
data class PathEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val title: String
)
