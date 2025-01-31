package com.woongi.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.woongi.domain.point.entity.Path

@Entity(tableName = "drawing_table")
data class Drawing(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "path") val path: Path
)