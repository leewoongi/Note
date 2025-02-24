package com.woongi.home.model.uiModel

import androidx.compose.ui.graphics.AndroidPath
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import com.woongi.domain.point.entity.Point
import com.woongi.domain.point.entity.constants.PathType

data class PathUiModel(
    val line: List<Point>,
    val color: Color,
    val thickness: Float,
    val opacity: Float
)
