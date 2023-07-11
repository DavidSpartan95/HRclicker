package com.example.hrclicker.dataBase.converters

import androidx.room.TypeConverter

class IntArrayConverter {
    @TypeConverter
    fun fromIntArray(array: Array<Int>): String {
        return array.joinToString(",")
    }

    @TypeConverter
    fun toIntArray(string: String): Array<Int> {
        return string.split(",").map { it.toInt() }.toTypedArray()
    }
}