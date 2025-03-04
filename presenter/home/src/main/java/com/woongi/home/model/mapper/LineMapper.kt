package com.woongi.home.model.mapper

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.woongi.domain.point.entity.Line
import com.woongi.domain.point.entity.Point
import com.woongi.home.model.uiModel.LineUiModel

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

fun LineUiModel.toLine() : Line {
    return Line(
        id = this.id,
        thickness = this.thickness,
        opacity = this.opacity,
        color = this.color.toArgb(),
        points = this.points.map { point ->
            Point(
                type = point.type,
                pointX = point.pointX,
                pointY = point.pointY
            )
        }
    )
}
