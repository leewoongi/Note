package com.woongi.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drawing_table")
data class Drawing(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "line") val line: String
)
