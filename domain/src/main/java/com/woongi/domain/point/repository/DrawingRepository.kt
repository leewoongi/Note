package com.woongi.domain.point.repository

import com.woongi.domain.point.entity.Canvas

interface DrawingRepository {
    suspend fun getAll() : List<Canvas>
    suspend fun save(lines: String)
}