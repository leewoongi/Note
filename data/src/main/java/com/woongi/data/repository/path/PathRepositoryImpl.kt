package com.woongi.data.repository.path

import com.woongi.data.local.room.dao.LineDao
import com.woongi.data.local.room.dao.PathDao
import com.woongi.data.local.room.dao.PointDao
import com.woongi.data.local.room.mapper.toLine
import com.woongi.data.local.room.mapper.toLineEntity
import com.woongi.data.local.room.mapper.toPath
import com.woongi.data.local.room.mapper.toPathEntity
import com.woongi.data.local.room.mapper.toPoint
import com.woongi.data.local.room.mapper.toPointEntity
import com.woongi.domain.point.entity.Path
import com.woongi.domain.point.repository.PathRepository
import javax.inject.Inject

class PathRepositoryImpl
@Inject constructor(
    private val pathDao: PathDao,
    private val lineDao: LineDao,
    private val pointDao: PointDao
) : PathRepository {

    override suspend fun getAll(): List<Path> {
        return pathDao.getAll().map { pathEntity ->
            val lines = lineDao.getLinesByPathId(pathEntity.id!!).map { lineEntity ->
                val points = pointDao.getPointsByLineId(lineEntity.id).map { pointEntity ->
                    pointEntity.toPoint()
                }
                lineEntity.toLine(points)
            }
            pathEntity.toPath(lines)
        }
    }

    override suspend fun save(path: Path) {
        val pathId = pathDao.insert(path.toPathEntity()).toInt()

        path.path.forEach { line ->
            val lineId = lineDao.insert(line.toLineEntity(pathId)).toInt()
            line.points.forEach { point ->
                pointDao.insert(point.toPointEntity(lineId = lineId))
            }
        }
    }

    override suspend fun delete(path: Path) {
        pathDao.delete(path.toPathEntity())
    }
}

