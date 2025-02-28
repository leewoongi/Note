package com.woongi.home.model.uiModel

import androidx.compose.ui.graphics.Color
import com.woongi.domain.point.entity.Point

data class PathUiModel(
    val id: Int, // 그린 순서
    val line: List<Point>,
    val color: Color,
    val thickness: Float,
    val opacity: Float
)
