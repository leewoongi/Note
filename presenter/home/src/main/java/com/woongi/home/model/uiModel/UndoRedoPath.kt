package com.woongi.home.model.uiModel

sealed class UndoRedoPath {
    data class Draw(val line: LineUiModel) : UndoRedoPath()
    data class Erase(val lines: List<LineUiModel>) : UndoRedoPath()
}