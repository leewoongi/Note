package com.woongi.domain.point.usecase

import com.woongi.domain.point.repository.DrawingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveUseCase
@Inject constructor(
    private val drawingRepository: DrawingRepository
){
    suspend fun save(lines: String) {
        drawingRepository.save(lines)
    }
}