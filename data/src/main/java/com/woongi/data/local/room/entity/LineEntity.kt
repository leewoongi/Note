package com.woongi.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * 선 데이터 - 점이 여러개 모인거
 * @param thickness: 선 두꼐
 * @param opacity: 선 투명도
 * @param color: 선 색
 */
@Entity(
    tableName = "lines", foreignKeys = [
        ForeignKey(
            entity = PathEntity::class,
            parentColumns = ["id"],
            childColumns = ["pathId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LineEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val lineId: Int, // 실제 선이 그려진 순서
    val thickness: Float,
    val opacity: Float,
    val color: Int,
    val pathId: Int
)
