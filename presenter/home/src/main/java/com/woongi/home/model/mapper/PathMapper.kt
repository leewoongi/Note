package com.woongi.home.model.mapper

import com.woongi.domain.point.entity.Path
import com.woongi.home.model.uiModel.PathUiModel


fun Path.toPathUiModel() : PathUiModel {
    return PathUiModel(
        id = this.id,
        title = this.title, // 기본 제목 설정
        lines = this.path.toListLineUiModel()
    )
}
