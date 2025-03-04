package com.woongi.home.model.uiModel

data class PathUiModel(
    val id: Int? = null,
    val title: String,
    val lines: List<LineUiModel>
){
    companion object {
        fun default() = PathUiModel(
            lines = emptyList(),
            title = ""
        )
    }
}