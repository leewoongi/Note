package com.woongi.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woongi.domain.point.entity.Path
import com.woongi.domain.point.usecase.DeleteUseCase
import com.woongi.domain.point.usecase.GetUseCase
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

@HiltViewModel
class DetailViewModel
@Inject constructor(
    private val getUseCase: GetUseCase,
    private val deleteUseCase: DeleteUseCase,
    private val navigator: Navigator
) : ViewModel() {
    private val _snackBar = MutableSharedFlow<String>(replay = 1)
    val snackBar = _snackBar.asSharedFlow()

    private val _lines = MutableStateFlow<List<Path>>(emptyList())
    val lines = _lines.asStateFlow()

    fun load() {
        viewModelScope.launch {
            try {
                _lines.value = getUseCase.getAll()
                if (_lines.value.isEmpty()) {
                    showSnackBar("불러 올 데이터가 없습니다.")
                } else {
                    showSnackBar("불러오기에 성공 했습니다.")
                }
            } catch (e: Exception) {
                showSnackBar("불러오기에 실패 했습니다.")
            }
        }
    }

    fun delete(path: Path) {
        viewModelScope.launch {
            deleteUseCase.invoke(path)
        }
    }

    fun navigateHome(
        item: Path? = null
    ) {
        navigator.createIntent(Destination.Main(
            NavigateItem(
                item = item
            )
        ))
    }

    fun showSnackBar(message: String) {
        viewModelScope.launch {
            _snackBar.emit(message)
        }
    }
}