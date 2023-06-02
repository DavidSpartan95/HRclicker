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
        //CE
        "BackPackReload" -> "Atk:20%+\nAcc:100\nThe player back pack reloads optimizing fire output"
        "CamoJumo" -> "EVA:10%+\nAcc:100\nPlayer grabs camo and becomes undetected for 30s"
        "ShilledBump" -> "Pow:25\nAcc:100\nPlayer seemingly teleports over the selling"
        "BOOL" -> "Pow:30\nAcc:100\nPlayer fly into the unknown with a banshee"
        //H2
        "SwordFly" -> "Pow:20\nAcc:70\nThe player perfectly performs a SwordFly saving a bit of time"
        "DoubleShot" -> "Pow:15\nAcc:95\nPlayer outputs double the fire power"
        "Ghosting" -> "EVA:12%+\nAcc:95\nPlayer vanish into a wall"
        "HCskip" -> "Pow:35\nAcc:90\nPlayer skips most of the current mission saving a lot of time"
        //H2A
        "SwordCancel" -> "Pow:15\nAcc:90\nPlayer performs a sword cancel saving a bit of time"
        "YY" -> "Pow:12\nAcc:100\nPlayer press Y two times"
        "PressureLaunch" -> "Atk:30%+\nAcc:100\nPlayer becomes invincible somehow"
        "PrisonSkip" -> "Pow:100\nAcc:25\nPlayer has a huge dislike for prisons"
        //H3
        "GravHammer" -> "Pow:15\nAcc:100\nPlayer swings hammer :)"
        "PowerDrain" -> "Heal:15\nAcc:100\nDrains away enemy energy shilled and heals the player"
        "BoxLaunch" -> "Pow:20\nAcc:90\nSmacking a Box and launch away with speed"
        "DCLaunch" -> "Pow:30\nAcc:95\nPlayer use a DC and Launch away with great speed"
        //ODST
        "AI_teleport" -> "EVA:8%+\nAcc:100\nFriendly AI teleports to safety"
        "BansheeGrab" -> "Pow:20\nAcc:80\nPlayer successfully grabs a Banshee speeding away"
        "ConeLaunch" -> "Pow:20\nAcc:90\nFlips away!"
        "CoastalOOB" -> "Pow:33\nAcc:97\nSmacking a Box and launch away with speed"
        //Infinite
        "GrappleHook" -> "EVA:9%+\nAcc:100\nSpeeding away with a Grapple"
        "GrappleGroundPound" -> "Pow:17\nAcc:100\nSmacking the ground and clips through"
        "InvincibleGlitch" -> "Atk:20%+\nAcc:100\nFor a priod of time the player can not take dmg"
        "TankGun" -> "Pow:30\nAcc:80\nPlayer finds a tank gun and puts in the pocket"
        //Reach
        "BobAppears" -> "Atk:10%+\nAcc:100\nBob Appears hyping everyone up"
        "BridgeSkip" -> "Pow:20\nAcc:80\nPlayer fly over a gap with good precision "
        "WCskip" -> "Pow:25\nAcc:95\nWait what? player grabs a falcon and takes off "
        "BuckLuck" -> "EVA:20%+\nAcc:100\nPlayer avoids bad luck"
        //H4
        "MuscleRunning" -> "Atk:15%+\nAcc:100\nPlayer resets Sprint cycle"
        "ConcBoost" -> "Pow:20\nAcc:80\nPlayer well placed Conc shots to elevate"
        "PhatomBumo" -> "Pow:25\nAcc:80\nPlayer bumps ona  Phatom to proceed"
        "DawnSkip" -> "Pow:50\nAcc:50\nPlayer skips huge part of the mission"
        //H5
        "RampSlide" -> "Pow:15\nAcc:95\nSlides of ramps saving seconds "
        "AngelJump" -> "EVA:15%\nAcc:100\nSlowly disappears above the sealing"
        "VoidSkip" -> "Pow:30\nAcc:65\nEnemys just falls into a void and dies "
        "GhostTele" -> "Pow:40\nAcc:65\nThe player teleports up in the sky"





        else-> ""
    }
    return text
}

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
fun atkDouble(moveDescription: String): Double{
    val regex = Regex("Atk:(\\d+)")
    val matchResult = regex.find(moveDescription)
    val atkString = matchResult?.groupValues?.getOrNull(1)
    println(atkString)
    if (atkString?.toDoubleOrNull() != null){
        val statBoost = (atkString.toDoubleOrNull()!! /100)+1.0
        return statBoost
    }else{
        return 1.0
    }
}
fun evaDouble(moveDescription: String): Double{
    val regex = Regex("EVA:(\\d+)")
    val matchResult = regex.find(moveDescription)
    val evaString = matchResult?.groupValues?.getOrNull(1)
    if (evaString?.toDoubleOrNull() != null){
        val statBoost = (evaString.toDoubleOrNull()!! /100)+1.0
        return statBoost
    }else{
        return 1.0
    }
}
fun healInt(moveDescription: String): Int {
    val regex = Regex("Heal:(\\d+)")
    val matchResult = regex.find(moveDescription)
    val healString = matchResult?.groupValues?.getOrNull(1)
    return healString?.toIntOrNull() ?: 0
}


fun moveUnlockCheck(
    points:Int,
    userRepository: UserRepository,
    category: String ,
    context:Context,
    update:()->Unit){

    if (category == "CE"){
        when(points){
            100 -> addMove("BackPackReload", userRepository,context){update.invoke()}
            850 -> addMove("CamoJumo", userRepository,context){update.invoke()}
            1500 -> addMove("ShilledBump", userRepository,context){update.invoke()}
            4000 -> addMove("BOOL", userRepository,context){update.invoke()}
            else -> update.invoke()
        }
    }

    if (category == "H2" ){
        when(points){
            100 -> addMove("SwordFly", userRepository,context){update.invoke()}
            750 -> addMove("DoubleShot", userRepository,context){update.invoke()}
            1500 -> addMove("Ghosting", userRepository,context){update.invoke()}
            5650 -> addMove("HCskip", userRepository,context){update.invoke()}
            else -> update.invoke()
        }
    }
    if (category == "H2A"){
        when(points){
            200 -> addMove("SwordCancel", userRepository,context){update.invoke()}
            1000 -> addMove("YY", userRepository,context){update.invoke()}
            1500 -> addMove("PressureLaunch", userRepository,context){update.invoke()}
            5650 -> addMove("PrisonSkip", userRepository,context){update.invoke()}
            else -> update.invoke()
        }
    }
    if (category == "H3"){
        when(points){
            100 -> addMove("GravHammer", userRepository,context){update.invoke()}
            1000 -> addMove("PowerDrain", userRepository,context){update.invoke()}
            1500 -> addMove("BoxLaunch", userRepository,context){update.invoke()}
            3800 -> addMove("DCLaunch", userRepository,context){update.invoke()}
            else -> update.invoke()
        }
    }
    if (category == "ODST"){
        when(points){
            100 -> addMove("AI_teleport", userRepository,context){update.invoke()}
            800 -> addMove("BansheeGrab", userRepository,context){update.invoke()}
            2000 -> addMove("ConeLaunch", userRepository,context){update.invoke()}
            4100 -> addMove("CoastalOOB", userRepository,context){update.invoke()}
            else -> update.invoke()
        }
    }
    if (category == "Infinite"){
        when(points){
            100 -> addMove("GrappleHook", userRepository,context){update.invoke()}
            600 -> addMove("GrappleGroundPound", userRepository,context){update.invoke()}
            700 -> addMove("InvincibleGlitch", userRepository,context){update.invoke()}
            1000 -> addMove("TankGun", userRepository,context){update.invoke()}
            else -> update.invoke()
        }
    }
    if (category == "H4"){
        when(points){
            50 -> addMove("MuscleRunning", userRepository,context){update.invoke()}
            800 -> addMove("ConcBoost", userRepository,context){update.invoke()}
            2000 -> addMove("PhantomBumo", userRepository,context){update.invoke()}
            3600 -> addMove("DawnSkip", userRepository,context){update.invoke()}
            else -> update.invoke()
        }
    }
    if (category == "Reach"){
        when(points){
            50 -> addMove("BobAppears", userRepository,context){update.invoke()}
            800 -> addMove("WCskip", userRepository,context){update.invoke()}
            2000 -> addMove("BridgeSkip", userRepository,context){update.invoke()}
            4100 -> addMove("BuckLuck", userRepository,context){update.invoke()}
            else -> update.invoke()
        }
    }
    if (category == "H5"){
        when(points){
            100 -> addMove("RampSlide", userRepository,context){update.invoke()}
            800 -> addMove("AngelJump", userRepository,context){update.invoke()}
            2000 -> addMove("VoidSkip", userRepository,context){update.invoke()}
            4100 -> addMove("GhostTele", userRepository,context){update.invoke()}
            else -> update.invoke()
        }
    }
    update.invoke()
}

fun addMove(name:String, repository: UserRepository, context: Context, update: () -> Unit){

    repository.performDatabaseOperation(Dispatchers.IO){

        repository.learnNewMove(name)
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(context,"You unlocked $name !", Toast.LENGTH_LONG).show()
            update.invoke()
        }
    }
}