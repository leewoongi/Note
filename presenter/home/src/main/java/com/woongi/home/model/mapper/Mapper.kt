package com.woongi.home.model.mapper

import androidx.compose.ui.graphics.Color
import com.woongi.domain.point.entity.Path
import com.woongi.home.model.uiModel.PathUiModel

//fun Path.toPathUiMapper() : PathUiModel {
//    val path = path.firstOrNull() ?: error("Path must contain at least one line")
//
//    // Path의 id, 첫 번째 Line의 속성들을 PathUiModel로 변환
//    return PathUiModel(
//        id = id, // Path의 id 그대로 사용
//        line = firstLine.points, // Line의 points 리스트 그대로 사용
//        color = Color(firstLine.color), // Line의 color를 Color로 변환
//        thickness = firstLine.thickness, // Line의 thickness 그대로 사용
//        opacity = firstLine.opacity // Line의 opacity 그대로 사용
//    )
//}