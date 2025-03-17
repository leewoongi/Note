package com.woongi.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woongi.domain.point.entity.Path
import com.woongi.domain.point.entity.constants.PathType
import com.woongi.domain.point.usecase.SaveUseCase
import com.woongi.home.model.constants.DialogType
import com.woongi.home.model.constants.DrawingType
import com.woongi.home.model.mapper.toLine
import com.woongi.home.model.mapper.toPathUiModel
import com.woongi.home.model.uiModel.CanvasUiModel
import com.woongi.home.model.uiModel.LineUiModel
import com.woongi.home.model.uiModel.PathUiModel
import com.woongi.home.model.uiModel.PointUiModel
import com.woongi.home.model.uiModel.SaveDialogUiModel
import com.woongi.home.model.uiModel.UndoRedoPath
import com.woongi.navigator.NavigateItem
import com.woongi.navigator.api.Destination
import com.woongi.navigator.api.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
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

    private val _uiModel = MutableStateFlow(CanvasUiModel.default())
    val uiModel = _uiModel.asStateFlow()

    private val _points: MutableList<PointUiModel> = mutableListOf()
    private val _lines: MutableList<LineUiModel> = mutableListOf()

    private val _snackBar = MutableSharedFlow<String>(replay = 1)
    val snackBar = _snackBar.asSharedFlow()

    private val _saveDialog = MutableSharedFlow<SaveDialogUiModel?>(replay = 1)
    val saveDialog = _saveDialog.asSharedFlow()

    fun setNavigateItem(navigateItem: NavigateItem) {
        val item = navigateItem.item ?: return

        if (item is Path) {
            val path = item.toPathUiModel()
            loadInitializeSaveData(path)
            _uiModel.value = _uiModel.value.copy(
                path = path,
                title = path.title
            )
        }
    }

    // 저장된 데이터 불러 온 후 수정 하기 전에 초기화 작업
    private fun loadInitializeSaveData(item: PathUiModel) {
        _lines.addAll(item.lines)
    }

    fun updateThickness(thickness: Float) {
        _uiModel.value = _uiModel.value.copy(
            currentThickness = thickness
        )
    }

    fun updateOpacity(opacity: Float) {
        _uiModel.value = _uiModel.value.copy(
            currentOpacity = opacity
        )
    }

    // 캔버스에 그리는 용도
    private fun drawPath() {
        _uiModel.value = _uiModel.value.copy(
            path = _uiModel.value.path.copy(
                lines = _lines
            )
        )
    }


    // 점 데이터 기록
    fun recordPoint(
        type: PathType,
        currentX: Float,
        currentY: Float
    ) {
        _points.add(
            PointUiModel(
                type = type,
                pointX = currentX,
                pointY = currentY
            )
        )
    }

    // 선 데이터 기록
    fun recordLine() {
        val id = _lines.size
        val newColor = _uiModel.value.currentColor

        val newLine = LineUiModel(
            id = id,
            points = _points.toList(), // 선은 점의 모임
            color = newColor,
            thickness = _uiModel.value.currentThickness,
            opacity = _uiModel.value.currentOpacity
        )

        _lines.add(newLine)
        _points.clear()
        _uiModel.value = _uiModel.value.copy(
            undo = _uiModel.value.undo + UndoRedoPath.Draw(newLine),
            redo = emptyList()
        )

        drawPath()
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
        _uiModel.value = _uiModel.value.copy(
            currentColor = newColor
        )
    }

    // 지우기 그리기 모드가 있음
    fun updateMode(type: DrawingType) {
        _uiModel.value = _uiModel.value.copy(
            type = type
        )
    }

    // 이전 상태로
    fun undo() {
        val lastAction = _uiModel.value.undo.lastOrNull() ?: return
        val newUndoList = _uiModel.value.undo.dropLast(1)
        val newRedoList = _uiModel.value.redo + lastAction

        when (lastAction) {
            is UndoRedoPath.Draw -> _lines.removeIf { it.id == lastAction.line.id }
            is UndoRedoPath.Erase -> _lines += lastAction.lines.sortedBy { it.id }
        }

        _uiModel.value = _uiModel.value.copy(undo = newUndoList, redo = newRedoList)
        drawPath()
    }

    // 복구
    fun redo() {
        val lastAction = _uiModel.value.redo.lastOrNull() ?: return
        val newRedoList = _uiModel.value.redo.dropLast(1)
        val newUndoList = _uiModel.value.undo + lastAction


        when (lastAction) {
            is UndoRedoPath.Draw -> _lines += lastAction.line
            is UndoRedoPath.Erase -> _lines.removeIf { line -> lastAction.lines.any { it.id == line.id } }
        }

        _uiModel.value = _uiModel.value.copy(undo = newUndoList, redo = newRedoList)
        drawPath()
    }

    // 선 지우기
    // 겹친 경우에는 다 지워지기
    fun erase(
        currentX: Float,
        currentY: Float
    ) {
        val threshold = 30f // 지우개 지름 나중에 사이즈 조절 가능하게 해야함

        // 지워야 할 경로를 찾기
        val erased = _lines.filter { line ->
            line.points.any { point ->
                distanceBetween(currentX, currentY, point.pointX, point.pointY) < threshold
            }
        }

        if (erased.isNotEmpty()) {
            _uiModel.value = _uiModel.value.copy(
                undo = _uiModel.value.undo + UndoRedoPath.Erase(erased),
                redo = emptyList()
            )

            _lines.removeAll { line -> line in erased }
            drawPath()
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

            val pathId = _uiModel.value.path.id
            _saveDialog.emit(
                SaveDialogUiModel(
                    type = if (pathId == null) DialogType.CREATE else DialogType.MODIFY,
                    dialogTitle = "저장",
                    subTitle = "제목을 입력하세요",
                    pathTitle = if(pathId == null) "" else _uiModel.value.title,
                    positiveName = "새로저장",
                    isVisibleNegativeButton = pathId != null,
                    negativeName = if (pathId == null) "" else "덮어쓰기"
                )
            )
        }
    }

    fun savePath(title: String) = saveOrUpdatePath(null, title) // 저장
    fun coverPath(title: String) = saveOrUpdatePath(_uiModel.value.path.id, title) // 수정 (덮어쓰기)

    private fun saveOrUpdatePath(id: Int?, title: String) {
        viewModelScope.launch {
            try {
                saveUseCase.save(
                    Path(
                        id = id,
                        title = title,
                        path = _lines.map { line -> line.toLine() }
                    )
                )
                _snackBar.emit("저장에 성공 했습니다.")
            } catch (e: Exception) {
                _snackBar.emit("저장에 실패 했습니다.")
            }
        }
    }

    fun closeDialog() {
        viewModelScope.launch {
            _saveDialog.emit(null)
        }
    }

    fun navigateDetail() {
        navigator.createIntent(
            Destination.Detail(
                NavigateItem(item = null)
            )
        )
    }
}