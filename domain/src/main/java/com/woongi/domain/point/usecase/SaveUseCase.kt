package com.woongi.domain.point.usecase

import com.woongi.domain.point.entity.Path
import com.woongi.domain.point.repository.PathRepository
import javax.inject.Inject

class SaveUseCase
@Inject constructor(
    private val pathRepository: PathRepository
){
    suspend fun save(path: Path) {
        pathRepository.save(path)
    }
}