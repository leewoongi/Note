package com.woongi.data.local.room.convert

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.woongi.domain.point.entity.Path

@ProvidedTypeConverter
class Converts(private val gson: Gson) {

    @TypeConverter
    fun fromPath(path: Path): String {
        val type = object : TypeToken<Path>() {}.type
        return gson.toJson(path, type)
    }

    @TypeConverter
    fun toPath(json: String): Path {
        val type = object : TypeToken<Path>() {}.type
        return gson.fromJson(json, type)
    }
}
