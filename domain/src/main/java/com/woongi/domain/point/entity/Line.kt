package com.woongi.domain.point.entity

import java.io.Serializable

data class Line(
    val thickness: Float,
    val opacity: Float,
    val points: List<Point>
):Serializable
