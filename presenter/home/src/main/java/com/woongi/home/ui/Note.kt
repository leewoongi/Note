package com.woongi.home.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.AndroidPath
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import com.woongi.domain.point.entity.constants.PathType
import com.woongi.home.MainViewModel


@Composable
fun Note(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
){
    var currentPath by remember { mutableStateOf(AndroidPath()) }

    Canvas(
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { dragAmount: Offset ->
                        currentPath = AndroidPath().apply {
                            addPath(currentPath)
                            moveTo(dragAmount.x, dragAmount.y)
                        }

                        // room에 저장할 데이터
                        viewModel.recordPoint(
                            type = PathType.MOVE_TO,
                            currentX = dragAmount.x,
                            currentY = dragAmount.y
                        )
                    },
                    onDragEnd = {
                        viewModel.addPath(currentPath, viewModel.thickness.value)
                        viewModel.recordLine()
                        currentPath = AndroidPath()
                    },

                    onDragCancel = {  },
                    onDrag = { change: PointerInputChange, dragAmount: Offset ->
                        currentPath = AndroidPath().apply {
                            addPath(currentPath)
                            lineTo(change.position.x, change.position.y)
                        }
                        viewModel.recordPoint(
                            type = PathType.LINE_TO,
                            currentX = change.position.x,
                            currentY = change.position.y
                        )
                        change.consume()
                    }
                )
            }
    ){
        // 기존에 그린 선
        viewModel.paths.forEach {
            drawPath(
                path = it.path,
                color = it.color,
                style = Stroke(it.thickness)
            )
        }

        // 현재 그려지고 있는 선
        drawPath(
            path = currentPath,
            color = Color(viewModel.color.value),
            style = Stroke(width = viewModel.thickness.value) // 선 두께 설정
        )
    }
}