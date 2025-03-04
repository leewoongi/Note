package com.woongi.home.model.uiModel

import com.woongi.home.model.constants.DialogType

data class SaveDialogUiModel(
    val type: DialogType,
    val dialogTitle: String,
    val subTitle: String,
    val pathTitle: String,
    val positiveName: String,
    val isVisibleNegativeButton: Boolean,
    val negativeName: String?,
)
