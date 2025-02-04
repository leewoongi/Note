package com.woongi.data.repository.drawing

import com.woongi.data.local.room.dao.DrawingDao
import com.woongi.data.local.room.entity.Drawing
import com.woongi.domain.point.entity.Path
import com.woongi.domain.point.repository.PathRepository
import javax.inject.Inject

class PathRepositoryImpl
@Inject constructor(
    private val drawingDao: DrawingDao
) : PathRepository {
    override suspend fun getAll(): List<Path> {
        return drawingDao.getAll().map { it.path }
    }

    override suspend fun save(path: Path) {
        drawingDao.insertAll(
            Drawing(
                path = path
            )
        )
    }
}