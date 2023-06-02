package com.example.hrclicker.functions

import com.example.hrclicker.dataBase.User

fun containsEmoticons(input: String): Boolean {
    val emoticonRegex = Regex("[\\uD800-\\uDBFF][\\uDC00-\\uDFFF]")
    return emoticonRegex.containsMatchIn(input)
}

fun hasMoreThan16Characters(input: String): Boolean {
    return input.length > 16
}

fun totalPoints(user: User): Int = with(user) {
    H1atk + H2atk + H3atk + H4atk + H2Aatk + ODSTatk + infinite_atk + reachAtk + H5atk
}
fun pointsByGame(user: User, gameName: String): Int{

    when(gameName){
        "CE" -> return user.H1atk
        "H2" -> return user.H2atk
        "H2A"-> return user.H2Aatk
        "H3" -> return user.H3atk
        "H4" -> return user.H4atk
        "H5" -> return user.H5atk
        "Reach" -> return user.reachAtk
        "ODST" -> return user.ODSTatk
        "Infinite" -> return user.infinite_atk
        else -> return 0
    }
}
fun gameCap(points:Int, gameName: String): Boolean{

    return when(gameName){
        "CE" -> points > 4000
        "H2" -> points > 5650
        "H2A"-> points > 5650
        "H3" -> points > 3800
        "H4" -> points > 3600
        "H5" -> points > 4100
        "Reach" -> points > 4100
        "ODST" -> points > 4100
        "Infinite" -> points > 1000
        else -> false
    }
}