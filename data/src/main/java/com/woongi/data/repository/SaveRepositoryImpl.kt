package com.woongi.data.repository

import com.woongi.data.local.room.dao.DrawingDao
import com.woongi.data.local.room.entity.Drawing
import com.woongi.domain.point.repository.SaveRepository
import javax.inject.Inject

class SaveRepositoryImpl
@Inject constructor(
    private val drawingDao: DrawingDao
) : SaveRepository {

    override suspend fun save(lines: String) {
        drawingDao.insertAll(
            Drawing(
                line = lines
            )
        )
    }
}