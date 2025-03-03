package com.woongi.home.model.mapper

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.woongi.domain.point.entity.Line
import com.woongi.domain.point.entity.Path
import com.woongi.domain.point.entity.Point
import com.woongi.domain.point.entity.constants.PathType
import com.woongi.home.model.uiModel.LineUiModel
import com.woongi.home.model.uiModel.PathUiModel
import com.woongi.home.model.uiModel.PointUiModel


fun Path.toPathUiModel() : PathUiModel {
    return PathUiModel(
        id = this.id,
        title = this.title, // 기본 제목 설정
        lines = this.path.mapIndexed { index, line ->
            LineUiModel(
                id = index, // 그린 순서
                points = line.points.map { point ->
                    PointUiModel(
                        type = PathType.valueOf(point.type.name),
                        pointX = point.pointX,
                        pointY = point.pointY
                    )
                },
                color = Color(line.color),
                thickness = line.thickness,
                opacity = line.opacity
            )
        }
    )
}

fun List<Line>.toListLineUiModel() : List<LineUiModel> {
    return this.map { line ->
        LineUiModel(
            id = line.id,
            thickness = line.thickness,
            opacity = line.opacity,
            color = Color(line.color),
            points = line.points.map { point ->
                point.toPointUiModel()
            }
        )
    }
}

fun Line.toLineUiModel() : LineUiModel {
    return LineUiModel(
        id = this.id,
        thickness = this.thickness,
        opacity = this.opacity,
        color = Color(this.color),
        points = this.points.map { point ->
            point.toPointUiModel()
        }
    )
}

fun Point.toPointUiModel() : PointUiModel {
    return PointUiModel(
        type = this.type,
        pointX = this.pointX,
        pointY = this.pointY
    )
}