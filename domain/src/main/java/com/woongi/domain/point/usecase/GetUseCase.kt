package com.woongi.domain.point.usecase

import com.woongi.domain.point.entity.Path
import com.woongi.domain.point.repository.PathRepository
import javax.inject.Inject

class GetUseCase
@Inject constructor(
    private val pathRepository: PathRepository
){
    suspend fun getAll(): List<Path> {
        return pathRepository.getAll()
    }
}