package com.woongi.home.model.uiModel

import com.woongi.domain.point.entity.constants.PathType

data class PointUiModel(
    val type: PathType,
    val pointX: Float,
    val pointY: Float
)
