package com.woongi.data.local.room.mapper

import com.woongi.data.local.room.entity.PathEntity
import com.woongi.domain.point.entity.Line
import com.woongi.domain.point.entity.Path

fun Path.toPathEntity() : PathEntity {
    return if(this.id == null) {
        PathEntity(title = this.title)
    } else {
        PathEntity(
            id = this.id,
            title = this.title
        )
    }
}


fun PathEntity.toPath(
    lines: List<Line>
): Path {
    return Path(
        id = this.id,
        title = this.title,
        path = lines
    )
}