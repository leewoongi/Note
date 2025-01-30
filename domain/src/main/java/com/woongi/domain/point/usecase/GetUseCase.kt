package com.woongi.domain.point.usecase

import com.woongi.domain.point.entity.Canvas
import com.woongi.domain.point.repository.DrawingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUseCase
@Inject constructor(
    private val drawingRepository: DrawingRepository
){
    suspend fun getAll(): List<Canvas> {
        return drawingRepository.getAll()
    }
}