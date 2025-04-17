package com.woongi.home.model.uiModel

import android.net.Uri
import androidx.compose.ui.graphics.Color
import com.woongi.home.model.constants.DrawingType

data class CanvasUiModel(
    val type: DrawingType,
    val uri: Uri?,
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
            uri = null,
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
