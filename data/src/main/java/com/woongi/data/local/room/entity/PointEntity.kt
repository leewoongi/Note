package com.woongi.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.woongi.domain.point.entity.constants.PathType

/** 점 데이터
 * @property pothType : Line, Move
 * @param pointX: X좌표
 * @param pointY: Y좌표
 **/
@Entity(
    tableName = "points",
    foreignKeys = [
        ForeignKey(
            entity = LineEntity::class,
            parentColumns = ["id"],
            childColumns = ["lineId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PointEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: PathType,
    val pointX: Float,
    val pointY: Float,
    val lineId: Int
)
