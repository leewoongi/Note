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
    var path by remember { mutableStateOf(Path()) }
    val thickness by viewModel.thickness.collectAsState()
    val opacity by viewModel.opacity.collectAsState()

    Canvas(
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { dragAmount: Offset ->
                        path = Path().apply {
                            addPath(path)
                            moveTo(dragAmount.x, dragAmount.y)
                        }
                        viewModel.record(
                            type = PathType.MOVE_TO,
                            currentX = dragAmount.x,
                            currentY = dragAmount.y,
                            thickness = thickness,
                            opacity = opacity
                        )
                    },
                    onDragEnd = {},
                    onDragCancel = {  },
                    onDrag = { change: PointerInputChange, dragAmount: Offset ->
                        path = Path().apply {
                            addPath(path)
                            lineTo(change.position.x, change.position.y)
                        }
                        viewModel.record(
                            type = PathType.LINE_TO,
                            currentX = change.position.x,
                            currentY = change.position.y,
                            thickness = thickness,
                            opacity = opacity
                        )
                        change.consume()
                    }
                )
            }
    ){
        drawPath(
            path = path,
            color = Color.Blue,
            style = Stroke(
                width = thickness
            )
        )
    }
}