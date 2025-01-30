package com.woongi.domain.point.repository

import kotlinx.coroutines.flow.Flow

interface DrawingRepository {
    fun getAll() : Flow<List<String>>
    suspend fun save(lines: String)
}