package com.example.hrclicker.dataBase.moves

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson

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