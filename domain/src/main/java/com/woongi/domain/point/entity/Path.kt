package com.woongi.domain.point.entity

import java.io.Serializable

data class Path(
    val id: Int?, // 저장할때는 사용하지 않음
    val title: String,
    val path: List<Line>
): Serializable
