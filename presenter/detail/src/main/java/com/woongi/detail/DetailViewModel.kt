package com.woongi.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woongi.domain.point.entity.Canvas
import com.woongi.domain.point.usecase.GetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(
    private val getUseCase: GetUseCase
): ViewModel() {

    private val _lines = MutableStateFlow<List<Canvas>>(emptyList())
    val lines = _lines.asStateFlow()

    fun load() {
        viewModelScope.launch {
            _lines.value = getUseCase.getAll()
        }
    }
}