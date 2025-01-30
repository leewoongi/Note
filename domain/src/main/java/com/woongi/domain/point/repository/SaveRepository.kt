package com.woongi.domain.point.repository

interface SaveRepository {
    suspend fun save(lines: String)
}