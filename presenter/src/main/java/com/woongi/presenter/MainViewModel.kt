package com.woongi.presenter

import androidx.compose.ui.graphics.Path
import androidx.lifecycle.ViewModel
import com.woongi.domain.point.entity.Point
import com.woongi.domain.point.usecase.SaveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
   private val saveUseCase: SaveUseCase
): ViewModel() {

   private val _lines: MutableList<Point> = mutableListOf()

   fun recordPath(path: Path) {

   }


   fun save() {

   }
}