package com.woongi.data.repository

import com.woongi.data.local.room.dao.DrawingDao
import com.woongi.data.local.room.entity.Drawing
import com.woongi.domain.point.repository.DrawingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DrawingRepositoryImpl
@Inject constructor(
    private val drawingDao: DrawingDao
) : DrawingRepository {
    override fun getAll(): Flow<List<String>> {
        return drawingDao.getAll().map { drawings ->
            drawings.map { it.line } // Drawing의 line 필드만 추출
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