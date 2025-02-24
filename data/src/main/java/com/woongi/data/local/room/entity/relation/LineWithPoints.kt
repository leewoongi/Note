package com.woongi.data.local.room.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.woongi.data.local.room.entity.LineEntity
import com.woongi.data.local.room.entity.PointEntity

data class LineWithPoints(
    @Embedded val line: LineEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "lineId"
    )
    val points: List<PointEntity>
)