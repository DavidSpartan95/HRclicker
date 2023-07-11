package com.example.hrclicker.dataBase.converters

import androidx.room.TypeConverter

class StringArrayConverter {
    @TypeConverter
    fun fromStringArray(array: Array<String>): String {
        return array.joinToString(",")
    }

    @TypeConverter
    fun toStringArray(string: String): Array<String> {
        return string.split(",").toTypedArray()
    }
}