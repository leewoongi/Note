package com.woongi.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.AndroidPath
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.woongi.domain.point.entity.Line
import com.woongi.domain.point.entity.Point
import com.woongi.domain.point.entity.constants.PathType
import com.woongi.domain.point.usecase.SaveUseCase
import com.woongi.home.model.constants.NavigationEvent
import com.woongi.home.model.uiModel.PathUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val saveUseCase: SaveUseCase
) : ViewModel() {


    private val _paths = mutableStateListOf<PathUiModel>()
    val paths: List<PathUiModel> get() = _paths

    private val _points: MutableList<Point> = mutableListOf()
    private val _lines: MutableList<Line> = mutableListOf()


    private val _thickness: MutableStateFlow<Float> = MutableStateFlow(0f)
    val thickness = _thickness.asStateFlow()

    private val _opacity: MutableStateFlow<Float> = MutableStateFlow(0f)
    val opacity = _opacity.asStateFlow()

    private val _navigate = MutableSharedFlow<NavigationEvent>()
    val navigate = _navigate.asSharedFlow()

    private val _snackBar = MutableSharedFlow<String>(replay = 1)
    val snackBar = _snackBar.asSharedFlow()

    fun updateThickness(thickness: Float) {
        _thickness.value = thickness
    }

    fun updateOpacity(opacity: Float) {
        _opacity.value = opacity
    }

    // 캔버스에 그리는 용도
    fun addPath(
        path: AndroidPath,
        thickness: Float
    ) {
        _paths.add(
            PathUiModel(
                path = path,
                color = Color.Red, // 임시 색상
                thickness = thickness,
                opacity = 1f
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
            Point(
                type = type,
                pointX = currentX,
                pointY = currentY
            )
        )
    }

    // 선 데이터 기록
    fun recordLine(){
        _lines.add(
            Line(
                thickness = thickness.value,
                opacity = opacity.value,
                points = _points.map { it.copy() }
            )
        )
        _points.clear()
    }

    fun save() {
        viewModelScope.launch {
            if(_lines.isEmpty()) {
                _snackBar.emit("저장할 데이터가 없습니다.")
                return@launch
            }

            try{
                saveUseCase.save(
                    com.woongi.domain.point.entity.Path(
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
        viewModelScope.launch {
            _navigate.emit(NavigationEvent.DETAIL)
        }
    }
}