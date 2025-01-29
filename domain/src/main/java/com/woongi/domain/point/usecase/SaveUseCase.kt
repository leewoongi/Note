package com.woongi.domain.point.usecase

import com.woongi.domain.point.repository.SaveRepository
import javax.inject.Inject

class SaveUseCase
@Inject constructor(
    private val saveRepository: SaveRepository
){
    fun save() {
        saveRepository.save()
    }
}