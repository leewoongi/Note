package com.woongi.home.model.uiModel

import androidx.compose.ui.graphics.Color

data class LineUiModel(
    val id: Int, // 그린 순서
    val color: Color,
    val thickness: Float,
    val opacity: Float,
    val points: List<PointUiModel>
)
