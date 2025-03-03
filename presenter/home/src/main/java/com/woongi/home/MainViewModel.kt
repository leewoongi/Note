package com.woongi.home

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woongi.domain.point.entity.Line
import com.woongi.domain.point.entity.Path
import com.woongi.domain.point.entity.Point
import com.woongi.domain.point.entity.constants.PathType
import com.woongi.domain.point.usecase.SaveUseCase
import com.woongi.home.model.constants.DrawingType
import com.woongi.home.model.uiModel.PathUiModel
import com.woongi.home.model.uiModel.UndoRedoPath
import com.woongi.navigator.NavigateItem
import com.woongi.navigator.api.Destination
import com.woongi.navigator.api.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.sqrt

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val saveUseCase: SaveUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _drawingType = MutableStateFlow(DrawingType.DRAWING)
    val drawingType = _drawingType.asStateFlow()

    private val _paths = MutableStateFlow<List<PathUiModel>>(emptyList())
    val paths: StateFlow<List<PathUiModel>> get() = _paths.asStateFlow()

    private val _undo = MutableStateFlow<List<UndoRedoPath>>(emptyList())
    val undo: StateFlow<List<UndoRedoPath>> get() = _undo.asStateFlow()

    private val _redo = MutableStateFlow<List<UndoRedoPath>>(emptyList())
    val redo: StateFlow<List<UndoRedoPath>> get() = _redo.asStateFlow()

    private val _points: MutableList<Point> = mutableListOf()
    private val _lines: MutableList<Line> = mutableListOf()

    private val _thickness = MutableStateFlow(1f)
    val thickness: StateFlow<Float> get() = _thickness.asStateFlow()

    private val _opacity = MutableStateFlow(1f)
    val opacity: StateFlow<Float> get() = _opacity.asStateFlow()

    private val _color = MutableStateFlow(Color.Black.toArgb())
    val color: StateFlow<Int> get() = _color.asStateFlow()

    private val _snackBar = MutableSharedFlow<String>(replay = 1)
    val snackBar = _snackBar.asSharedFlow()

    fun setNavigateItem(navigateItem: NavigateItem) {
        val item = navigateItem.item ?: return
        println("TEST TEST TET setNavigateItem: $item")
    }

    fun updateThickness(thickness: Float) {
        _thickness.value = thickness
    }

    fun updateOpacity(opacity: Float) {
        _opacity.value = opacity
    }

    // 캔버스에 그리는 용도
    fun addLine() {
        val id = _paths.value.size
        val newPath = PathUiModel(
            id = id,
            line = _points.toList(), // 선은 점의 모임
            color = Color(color.value),
            thickness = _thickness.value,
            opacity = 1f
        )

        _paths.value += newPath
        _undo.value += UndoRedoPath.Draw(newPath)
        _redo.value = emptyList()
    }

    // 점 데이터 기록
    fun recordPoint(
        type: PathType,
        currentX: Float,
        currentY: Float
    ) {
        _points.add(
            Point(
                type = type,
                pointX = currentX,
                pointY = currentY
            )
        )
    }

    // 선 데이터 기록
    fun recordLine() {
        _lines.add(
            Line(
                thickness = thickness.value,
                opacity = opacity.value,
                points = _points.map { it.copy() },
                color = color.value
            )
        )
        _points.clear()
    }


    fun updateColor(
        color: Color,
        brightness: Float,
        opacity: Float
    ) {
        val newColor = color.copy(
            red = (color.red * brightness).coerceIn(0f, 1f),
            green = (color.green * brightness).coerceIn(0f, 1f),
            blue = (color.blue * brightness).coerceIn(0f, 1f),
            alpha = opacity
        )
        _color.value  = newColor.toArgb()
    }

    // 지우기 그리기 모드가 있음
    fun updateMode(type: DrawingType) {
        _drawingType.value = type
    }

    // 이전 상태로
    fun undo() {
        val lastLine = _undo.value.last()
        _undo.value = _undo.value.dropLast(1)
        _redo.value += lastLine

        when(lastLine){
            is UndoRedoPath.Draw -> {
                _paths.value = _paths.value.filterNot { it.id == lastLine.path.id }
            }
            is UndoRedoPath.Erase -> {
                _paths.value = (_paths.value + lastLine.paths).sortedBy { it.id }
            }
        }
    }

    // 복구
    fun redo() {
        val lastLine = _redo.value.last()
        _redo.value = _redo.value.dropLast(1)
        _undo.value += lastLine

        when(lastLine){
            is UndoRedoPath.Draw -> {
                _paths.value += lastLine.path
            }
            is UndoRedoPath.Erase -> {
                lastLine.paths.forEach { line ->
                    _paths.value = _paths.value.filter { it.id != line.id }
                }
            }
        }
    }

    // 선 지우기
    // 겹친 경우에는 다 지워지기
    fun erase(
        currentX: Float,
        currentY: Float
    ) {
        val threshold = 30f // 지우개 지름 나중에 사이즈 조절 가능하게 해야함

        // 지워야 할 경로를 찾기
        val erased = _paths.value.filter { path ->
            path.line.any { point ->
                distanceBetween(currentX, currentY, point.pointX, point.pointY) < threshold
            }
        }

        if (erased.isNotEmpty()) {
            _undo.value += UndoRedoPath.Erase(erased)
            _redo.value = emptyList()
            _paths.value = _paths.value.filter { path -> path !in erased }
        }
    }

    // 지우개 중심좌표(현재 좌표) 에서 point들의 거리 구하기
    private fun distanceBetween(
        currentX: Float,
        currentY: Float,
        pointX: Float,
        pointY: Float
    ): Float {
        return sqrt(
            ((pointX - currentX) * (pointX - currentX) + (pointY - currentY) * (pointY - currentY)).toDouble()
        ).toFloat()
    }


    fun save() {
        viewModelScope.launch {
            if (_lines.isEmpty()) {
                _snackBar.emit("저장할 데이터가 없습니다.")
                return@launch
            }

            try {
                saveUseCase.save(
                    Path(
                        title = "TEST TEST TEST",
                        path = _lines
                    )
                )
                _snackBar.emit("저장에 성공 했습니다.")
            } catch (e: Exception) {
                _snackBar.emit("저장에 실패 했습니다.")
            }
        }
    }

    fun navigateDetail() {
        navigator.createIntent(Destination.Detail(
            NavigateItem(item = null)
        ))
    }
}