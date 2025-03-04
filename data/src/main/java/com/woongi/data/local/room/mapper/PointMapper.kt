package com.woongi.data.local.room.mapper

import com.woongi.data.local.room.entity.PointEntity
import com.woongi.domain.point.entity.Point


fun Point.toPointEntity(
    lineId: Int,
) : PointEntity {
    return PointEntity(
        type = this.type,
        pointX = this.pointX,
        pointY = this.pointY,
        lineId = lineId
    )
}

fun PointEntity.toPoint(): Point {
    return Point(
        type = this.type,
        pointX = this.pointX,
        pointY = this.pointY
    )
}