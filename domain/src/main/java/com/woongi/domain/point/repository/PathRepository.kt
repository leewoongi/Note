package com.woongi.domain.point.repository

import com.woongi.domain.point.entity.Path

interface PathRepository {
    suspend fun getAll() : List<Path>
    suspend fun save(lines: Path)
}