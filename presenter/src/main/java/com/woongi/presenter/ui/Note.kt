package com.woongi.presenter.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.woongi.presenter.MainViewModel

@Composable
fun Note(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
){
    var path by remember { mutableStateOf(Path()) }

    Canvas(
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { dragAmount: Offset ->
                        path = Path().apply {
                            addPath(path)
                            moveTo(dragAmount.x, dragAmount.y)
                        }
                    },
                    onDragEnd = {
                        viewModel.recordPath(path)
                    },
                    onDragCancel = {  },
                    onDrag = { change: PointerInputChange, dragAmount: Offset ->
                        path = Path().apply {
                            addPath(path)
                            lineTo(change.position.x, change.position.y)
                        }
                        change.consume()
                    }
                )
            }
    ){
        drawPath(
            path = path,
            color = Color.Blue,
            style = Stroke(width = 5f) // 선 두께 설정
        )
    }
}