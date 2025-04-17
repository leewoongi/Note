package com.woongi.home.model.uiModel

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import com.woongi.home.model.constants.DrawingType

data class CanvasUiModel(
    val type: DrawingType,
    val bitmap: ImageBitmap?,
    val path: PathUiModel,
    val title: String,
    val undo: List<UndoRedoPath>,
    val redo: List<UndoRedoPath>,
    val currentThickness: Float,
    val currentOpacity: Float,
    val currentColor: Color
){
    companion object {
        fun default() = CanvasUiModel(
            type = DrawingType.DRAWING,
            bitmap = null,
            path = PathUiModel.default(),
            title = "",
            undo = emptyList(),
            redo = emptyList(),
            currentThickness = 1f,
            currentOpacity = 1f,
            currentColor = Color.Black
        )
    }
}
