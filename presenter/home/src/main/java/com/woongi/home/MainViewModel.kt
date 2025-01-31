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
import kotlinx.coroutines.flow.asSharedFlow
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

    fun record(
        type: PathType,
        currentX: Float,
        currentY: Float
    ) {
        _lines.add(
            Point(
                type = type,
                pointX = currentX,
                pointY = currentY
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

    fun navigateDetail() {
        viewModelScope.launch {
            _navigate.emit(NavigationEvent.DETAIL)
        }
    }
}