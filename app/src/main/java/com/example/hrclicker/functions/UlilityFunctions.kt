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
        else -> return 69
    }
}