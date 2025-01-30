package com.woongi.data.repository.drawing

import com.woongi.data.local.room.dao.DrawingDao
import com.woongi.data.local.room.entity.Drawing
import com.woongi.domain.point.entity.Canvas
import com.woongi.domain.point.repository.DrawingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DrawingRepositoryImpl
@Inject constructor(
    private val drawingDao: DrawingDao
) : DrawingRepository {
    override suspend fun getAll(): List<Canvas> {
        return drawingDao.getAll().map { drawings ->
            drawings.toCanvas()
        }
    }


    override suspend fun save(lines: String) {
        drawingDao.insertAll(
            Drawing(
                line = lines
            )
        )
    }
}