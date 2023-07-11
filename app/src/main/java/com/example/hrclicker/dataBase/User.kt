package com.example.hrclicker.dataBase


import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.hrclicker.dataBase.converters.StringArrayConverter
import com.example.hrclicker.R
import com.example.hrclicker.dataBase.converters.ObjectConverter
import com.example.hrclicker.dataBase.converters.emblemInfo

@Entity
data class User(
    var name: String,
    var HP: Int,
    var H1atk:Int,
    var H2atk:Int,
    var H2Aatk:Int,
    var H3atk:Int,
    var ODSTatk:Int,
    var reachAtk:Int,
    var H4atk:Int,
    var H5atk:Int,
    var infinite_atk:Int,
    var ability: String = "",
    var move1: String = "Reroute",
    var move2: String = "Empty",
    var move3: String = "Empty",
    var move4: String = "Empty",
    var cap: Int = 50,
    @TypeConverters(StringArrayConverter::class)
    var moves: Array<String> = arrayOf("Reroute"),
    @TypeConverters(ObjectConverter::class)
    var emblem: emblemInfo = emblemInfo(
        R.drawable.background1,
        R.drawable.emblem1,
        Color.parseColor("#000000"), // Black color
        Color.parseColor("#FFFFFF") // White color
    )

) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}


