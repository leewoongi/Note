package com.woongi.home.model.mapper

import com.woongi.domain.point.entity.Point
import com.woongi.home.model.uiModel.PointUiModel

fun Point.toPointUiModel() : PointUiModel {
    return PointUiModel(
        type = this.type,
        pointX = this.pointX,
        pointY = this.pointY
    )
}