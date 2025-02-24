package com.woongi.data.repository.path

import com.woongi.data.local.room.dao.LineDao
import com.woongi.data.local.room.dao.PathDao
import com.woongi.data.local.room.dao.PointDao
import com.woongi.data.local.room.entity.LineEntity
import com.woongi.data.local.room.entity.PathEntity
import com.woongi.data.local.room.entity.PointEntity
import com.woongi.domain.point.entity.Line
import com.woongi.domain.point.entity.Path
import com.woongi.domain.point.entity.Point
import com.woongi.domain.point.repository.PathRepository
import javax.inject.Inject

class PathRepositoryImpl
@Inject constructor(
    private val pathDao: PathDao,
    private val lineDao: LineDao,
    private val pointDao: PointDao
) : PathRepository {

    override suspend fun getAll(): List<Path> {
        val pathEntities = pathDao.getAll()
        return pathEntities.map { pathEntity ->
            val lines = lineDao.getLinesByPathId(pathEntity.id)
            val path = Path(
                id = pathEntity.id,
                title = pathEntity.title,
                path = lines.map { lineEntity ->
                    val points = pointDao.getPointsByLineId(lineEntity.id)
                    Line(
                        thickness = lineEntity.thickness,
                        opacity = lineEntity.opacity,
                        color = lineEntity.color,
                        points = points.map { pointEntity ->
                            Point(
                                type = pointEntity.type,
                                pointX = pointEntity.pointX,
                                pointY = pointEntity.pointY
                            )
                        }
                    )
                }
            )
            path
        }
    }


    override suspend fun save(path: Path) {
        val pathEntity = PathEntity(title = path.title)
        val pathId = pathDao.insert(pathEntity)
        path.path.forEach { line ->
            val lineEntity = LineEntity(
                pathId = pathId.toInt(),
                thickness = line.thickness,
                opacity = line.opacity,
                color = line.color
            )

            val lineId = lineDao.insert(lineEntity)

            line.points.forEach { point ->
                val pointEntity = PointEntity(
                    lineId = lineId.toInt(),
                    type = point.type,
                    pointX = point.pointX,
                    pointY = point.pointY
                )
                pointDao.insert(pointEntity)
            }
        }
    }
}
