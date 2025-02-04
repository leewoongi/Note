package com.woongi.home.model.uiModel

import androidx.compose.ui.graphics.AndroidPath
import androidx.compose.ui.graphics.Color

data class PathUiModel(
    val path: AndroidPath,
    val color: Color,
    val thickness: Float,
    val opacity: Float
)
