package com.woongi.data.local.room.entity.relation

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.woongi.data.local.room.entity.LineEntity
import com.woongi.data.local.room.entity.PathEntity

data class PathWithLines(
    @Embedded val path: PathEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "pathId"
    )
    val lines: List<LineEntity>
)