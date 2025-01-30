package com.woongi.domain.point.entity

import com.woongi.domain.point.entity.constants.PathType

// 모든 선은 점으로 표현 가능 하기 때문에 로 관리할 예정
data class Point(
    val type: PathType,
    val pointX: Float,
    val pointY: Float
)
