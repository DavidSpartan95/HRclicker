package com.example.hrclicker.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.hrclicker.dataBase.moves.StringArrayConverter

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
    var move1: String = "Attack",
    var move2: String = "Empty",
    var move3: String = "Empty",
    var move4: String = "Empty",
    @TypeConverters(StringArrayConverter::class)
    var moves: Array<String> = arrayOf("Attack")

) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
