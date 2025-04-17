package com.woongi.home.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.IntSize
import com.woongi.domain.point.entity.constants.PathType
import com.woongi.home.MainViewModel
import com.woongi.home.model.constants.DrawingType
import com.woongi.home.model.mapper.resizeImageBitmap


@Composable
fun Note(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
){
    var currentPath by remember { mutableStateOf(Path()) }
    var erasePath by remember { mutableStateOf<Offset?>(null) }
    val uiModel by viewModel.uiModel.collectAsState()

    var canvasSize by remember { mutableStateOf(IntSize(0, 0)) }
    val resizedBitmap = remember(uiModel.bitmap, canvasSize) {
        uiModel.bitmap?.let { bitmap ->
            val canvasWidth = canvasSize.width
            val canvasHeight = canvasSize.height

            val imageWidth = bitmap.width
            val imageHeight = bitmap.height

            val scale = minOf(
                canvasWidth.toFloat() / imageWidth,
                canvasHeight.toFloat() / imageHeight
            )
            val scaledWidth = (imageWidth * scale).toInt()
            val scaledHeight = (imageHeight * scale).toInt()

            resizeImageBitmap(bitmap, scaledWidth, scaledHeight) to
                    Offset(
                        x = (canvasWidth - scaledWidth) / 2f,
                        y = (canvasHeight - scaledHeight) / 2f
                    )
        }
    }

    Canvas(
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { dragAmount: Offset ->
                        if(uiModel.type == DrawingType.DRAWING) {
                            currentPath = Path().apply {
                                addPath(currentPath)
                                moveTo(dragAmount.x, dragAmount.y)
                            }

                            // room에 저장할 데이터
                            viewModel.recordPoint(
                                type = PathType.MOVE_TO,
                                currentX = dragAmount.x,
                                currentY = dragAmount.y
                            )
                        } else {
                            erasePath = Offset(dragAmount.x, dragAmount.y)
                        }
                    },
                    onDragEnd = {
                        if(uiModel.type == DrawingType.DRAWING) {
                            viewModel.recordLine()
                            currentPath = Path()
                        } else {
                            erasePath = null
                        }
                    },

                    onDragCancel = {  },
                    onDrag = { change: PointerInputChange, dragAmount: Offset ->
                        if(uiModel.type == DrawingType.DRAWING) {
                            currentPath = Path().apply {
                                addPath(currentPath)
                                lineTo(change.position.x, change.position.y)
                            }
                            viewModel.recordPoint(
                                type = PathType.LINE_TO,
                                currentX = change.position.x,
                                currentY = change.position.y
                            )
                        } else {
                            erasePath = Offset(change.position.x, change.position.y)
                            viewModel.erase(change.position.x, change.position.y)
                        }
                        change.consume()
                    }
                )
            }
    ){
        canvasSize = IntSize(size.width.toInt(), size.height.toInt())
        resizedBitmap?.let { (bitmap, offset) ->
            drawImage(
                image = bitmap,
                topLeft = offset
            )
        }

        // 기존에 그린 선
        uiModel.path.lines.forEach { line ->
            val draw = Path()
            line.points.forEach { point ->
                when(point.type){
                    PathType.MOVE_TO -> { draw.moveTo(point.pointX, point.pointY) }
                    PathType.LINE_TO -> { draw.lineTo(point.pointX, point.pointY) }
                }
            }

            drawPath(
                path = draw,
                color = line.color,
                style = Stroke(line.thickness)
            )
        }

        // 현재 그려지고 있는 선
        if(uiModel.type == DrawingType.DRAWING) {
            drawPath(
                path = currentPath,
                color = uiModel.currentColor,
                style = Stroke(width = uiModel.currentThickness) // 선 두께 설정
            )
        } else {
            erasePath?.let {
                drawCircle(
                    color = Color.Gray.copy(alpha = 0.6f), // 외곽선 색상
                    radius = 30f, // 외곽선 반경 (내부보다 크게 설정)
                    center = Offset(erasePath!!.x, erasePath!!.y),
                    style = Stroke(width = 5f) // 외곽선 두께
                )

                drawCircle(
                    color = Color.White.copy(alpha = 0.5f), // 내부 빨간색 반투명
                    radius = 25f, // 내부 반경
                    center = Offset(erasePath!!.x, erasePath!!.y)
                )
            }
        }
    }
}