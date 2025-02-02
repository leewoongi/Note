package com.woongi.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woongi.domain.point.entity.Path
import com.woongi.domain.point.entity.Point
import com.woongi.domain.point.entity.constants.PathType
import com.woongi.domain.point.usecase.SaveUseCase
import com.woongi.home.model.constants.NavigationEvent
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

    private val _lines: MutableList<Point> = mutableListOf()
    private val _navigate = MutableSharedFlow<NavigationEvent>()
    val navigate = _navigate.asSharedFlow()

    private val _snackBar = MutableSharedFlow<String>(replay = 1)
    val snackBar = _snackBar.asSharedFlow()

    private val _thickness: MutableStateFlow<Float> = MutableStateFlow(0f)
    val thickness = _thickness.asStateFlow()

    private val _opacity: MutableStateFlow<Float> = MutableStateFlow(0f)
    val opacity = _opacity.asStateFlow()

    fun record(
        type: PathType,
        currentX: Float,
        currentY: Float,
        thickness: Float,
        opacity: Float
    ) {
        _lines.add(
            Point(
                type = type,
                pointX = currentX,
                pointY = currentY,
                thickness = thickness,
                opacity = opacity
            )
        )
    }

    fun save() {
        viewModelScope.launch {
            if(_lines.isEmpty()) {
                _snackBar.emit("저장할 데이터가 없습니다.")
                return@launch
            }

            try{
                saveUseCase.save(
                    Path(
                        path = _lines
                    )
                )
                _snackBar.emit("저장에 성공 했습니다.")
            } catch (e: Exception) {
                _snackBar.emit("저장에 실패 했습니다.")
            }
        }
    }

    fun updateThickness(thickness: Float) {
        _thickness.value = thickness
    }

    fun updateOpacity(opacity: Float) {
        _opacity.value = opacity
    }

    fun navigateDetail() {
        viewModelScope.launch {
            _navigate.emit(NavigationEvent.DETAIL)
        }
    }
}