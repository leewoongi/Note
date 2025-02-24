package com.woongi.data.local.room.convert

import androidx.room.Delete
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.woongi.domain.point.entity.Line
import com.woongi.domain.point.entity.Path
import com.woongi.domain.point.entity.Point

@Deprecated("사용안됨")
@ProvidedTypeConverter
class Converts(private val gson: Gson) {

    // Path 객체를 String으로 변환
    @TypeConverter
    fun fromPath(path: Path): String {
        val type = object : TypeToken<Path>() {}.type
        return gson.toJson(path, type)
    }

    // String을 Path 객체로 변환
    @TypeConverter
    fun toPath(json: String): Path {
        val type = object : TypeToken<Path>() {}.type
        return gson.fromJson(json, type)
    }

    // Line 객체를 String으로 변환
    @TypeConverter
    fun fromLine(line: Line): String {
        val type = object : TypeToken<Line>() {}.type
        return gson.toJson(line, type)
    }

    // String을 Line 객체로 변환
    @TypeConverter
    fun toLine(json: String): Line {
        val type = object : TypeToken<Line>() {}.type
        return gson.fromJson(json, type)
    }

    // Point 객체를 String으로 변환
    @TypeConverter
    fun fromPoint(point: Point): String {
        val type = object : TypeToken<Point>() {}.type
        return gson.toJson(point, type)
    }

    // String을 Point 객체로 변환
    @TypeConverter
    fun toPoint(json: String): Point {
        val type = object : TypeToken<Point>() {}.type
        return gson.fromJson(json, type)
    }
}
