package com.woongi.domain.point.entity

import java.io.Serializable

data class Line(
    val id: Int = 0,
    val thickness: Float,
    val opacity: Float,
    val color: Int ,
    val points: List<Point>
):Serializable
