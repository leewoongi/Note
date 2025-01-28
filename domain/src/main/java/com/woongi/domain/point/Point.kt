package com.woongi.domain.point

// 모든 선은 점으로 표현 가능 하기 때문에 List<Point>로 관리할 예정
data class Point(
    val id: Int,
    val pointX: Long,
    val pointY: Long
)
