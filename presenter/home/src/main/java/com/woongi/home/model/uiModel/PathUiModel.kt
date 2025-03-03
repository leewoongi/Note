package com.woongi.home.model.uiModel

data class PathUiModel(
    val id: Int? = null,
    val title: String = "TEST TEST TEST",
    val lines: List<LineUiModel>
){
    companion object {
        fun default() = PathUiModel(
            lines = emptyList()
        )
    }
}