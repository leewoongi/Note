package com.woongi.data.local.room.mapper

import com.woongi.data.local.room.entity.LineEntity
import com.woongi.domain.point.entity.Line
import com.woongi.domain.point.entity.Point

fun Line.toLineEntity(
    pathId: Int,
) : LineEntity {
    return LineEntity(
        lineId = this.id,
        thickness = this.thickness,
        opacity = this.opacity,
        color = this.color,
        pathId = pathId
    )
}

fun LineEntity.toLine(
    points: List<Point>
): Line {
    return Line(
        id = this.lineId,
        thickness = this.thickness,
        opacity = this.opacity,
        color = this.color,
        points = points
    )
}