package com.example.hrclicker.functions

import android.content.Context
import android.widget.Toast
import com.example.hrclicker.dataBase.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun moveDescription(moveName:String):String{
    val text:String = when(moveName){
        "Reroute" -> "Pow:10\nAcc:100\nThe player implements a faster route"
        "SwordFly" -> "Pow:50\nAcc:70\nThe player perfectly performs a SwordFly saving a bit of time"
        else-> ""
    }
    return text
}

//TODO write a function that returns the number that comes after "Pow:" from any given String
fun powInt(moveDescription: String): Int {
    val regex = Regex("Pow:(\\d+)")
    val matchResult = regex.find(moveDescription)
    val powString = matchResult?.groupValues?.getOrNull(1)
    return powString?.toIntOrNull() ?: 0
}
fun accInt(moveDescription: String): Int {
    val regex = Regex("Acc:(\\d+)")
    val matchResult = regex.find(moveDescription)
    val accString = matchResult?.groupValues?.getOrNull(1)
    return accString?.toIntOrNull() ?: 0
}


fun moveUnlockCheck(
    points:Int,
    userRepository: UserRepository,
    category: String ,
    context:Context,
    update:()->Unit){

    if (category == "H2" ||category == "H2A"){
        when(points){
            50 -> {
                userRepository.performDatabaseOperation(Dispatchers.IO){
                    val moveName = "SwordFly"
                    userRepository.learnNewMove(moveName)
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(context,"You unlocked $moveName !", Toast.LENGTH_LONG).show()
                        update.invoke()
                    }
                }
            }
            else -> update.invoke()
        }

    }
}