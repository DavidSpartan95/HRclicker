package com.example.hrclicker.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

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
    var ability: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
