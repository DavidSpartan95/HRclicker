package com.example.hrclicker.dataBase.converters

import android.graphics.Color
import androidx.room.TypeConverter

class ObjectConverter {
    @TypeConverter
    fun fromEmblemInfo(emblemInfo: emblemInfo): String {
        return "${emblemInfo.background},${emblemInfo.emblem},${emblemInfo.backgroundColor},${emblemInfo.emblemColor}"
    }

    @TypeConverter
    fun toEmblemInfo(string: String): emblemInfo {
        val parts = string.split(",")
        return emblemInfo(
            background = parts[0].toInt(),
            emblem = parts[1].toInt(),
            backgroundColor = parts[2].toInt(),
            emblemColor = parts[3].toInt()
        )
    }
}

class emblemInfo(
    var background: Int,
    var emblem: Int,
    var backgroundColor: Int,
    var emblemColor: Int,
)
