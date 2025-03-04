package com.woongi.domain.point.usecase

import com.woongi.domain.point.entity.Path
import com.woongi.domain.point.repository.PathRepository
import javax.inject.Inject

class DeleteUseCase
@Inject constructor(
    private val pathRepository: PathRepository
){
    suspend fun invoke(path: Path) {
        pathRepository.delete(path)
    }
}