package com.woongi.home.model.uiModel

sealed class UndoRedoPath {
    data class Draw(val path: PathUiModel) : UndoRedoPath()
    data class Erase(val paths: List<PathUiModel>) : UndoRedoPath()
}