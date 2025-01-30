package com.woongi.domain.point.usecase

import com.woongi.domain.point.repository.DrawingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveUseCase
@Inject constructor(
    private val drawingRepository: DrawingRepository
){
    fun getAll(): Flow<List<String>> {
        return drawingRepository.getAll()

    }

    suspend fun save(lines: String) {
        drawingRepository.save(lines)
    }
}