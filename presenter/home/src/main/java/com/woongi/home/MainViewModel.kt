package com.woongi.home

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.woongi.domain.point.repository.DrawingRepository
import com.woongi.domain.point.usecase.SaveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val saveUseCase: SaveUseCase
) : ViewModel() {

    private val _lines: MutableList<Path> = mutableListOf()

    fun record(path: Path) {
        _lines.add(path)
    }

    fun save() {
        val gson = Gson()
        val map = mapOf("lines" to _lines)

        val jsonLine = gson.toJson(map)
        viewModelScope.launch {
            saveUseCase.save(jsonLine)
        }
    }

    fun load() {
        viewModelScope.launch {
           saveUseCase.getAll().collectLatest { it->

            }
        }
    }
}