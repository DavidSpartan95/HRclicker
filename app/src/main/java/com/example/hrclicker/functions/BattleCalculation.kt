package com.example.hrclicker.functions

import com.example.hrclicker.dataBase.User
import com.example.hrclicker.runnerData.Runner
import kotlin.random.Random


fun DamageClac(move:String,player: User, boss: Runner, mode: String, boost:Double, heal:Boolean? = null): Int{
    var pow = if (heal == true){
          healInt(moveDescription(move)).toDouble()
    }else {
          powInt(moveDescription(move)).toDouble()
    }
    println(boost)
    pow *= boost
    val acc = accInt(moveDescription(move))
    var atk: Int
    var def: Int

    when (mode) {
        "CE" -> {
            atk = player.H1atk
            def = boss.H1atk
        }
        "H2" -> {
            atk = player.H2atk
            def = boss.H2atk
        }
        "H2A" -> {
            atk = player.H2Aatk
            def = boss.H2Aatk
        }
        "H3" -> {
            atk = player.H3atk
            def = boss.H3atk
        }
        "H4" -> {
            atk = player.H4atk
            def = boss.H4atk
        }
        "H5" -> {
            atk = player.H5atk
            def = boss.H5atk
        }
        "Reach" -> {
            atk = player.reachAtk
            def = boss.reachAtk
        }
        "Infinite" -> {
            atk = player.infinite_atk
            def = boss.infinite_atk
        }
        "ODST" -> {
            atk = player.ODSTatk
            def = boss.ODSTatk
        }
        else -> {
            atk = 0
            def = 0
        }
    }

    atk = atk.coerceAtLeast(1)
    def = def.coerceAtLeast(1)
    val randomFactor = Random.nextDouble(0.9, 1.1) // Random factor between 0.9 and 1.1
    var damage = (pow * Effectiveness(atk.toDouble(), def.toDouble()) * randomFactor).toInt()

    //Acc check
    val accRoll = Random.nextInt(0,100)
    if (acc < accRoll){ damage = 0}
    println("Damage is $damage")

    return damage

}

fun DamageClacBoss(player: User, boss: Runner, mode: String,eva: Double): Int{
    var pow = 10
    var atk: Int
    var def: Int
    when (mode) {
        "CE" -> {
            def = player.H1atk
            atk = boss.H1atk
        }
        "H2" -> {
            def = player.H2atk
            atk = boss.H2atk
        }
        "H2A" -> {
            def = player.H2Aatk
            atk = boss.H2Aatk
        }
        "H3" -> {
            def = player.H3atk
            atk = boss.H3atk
        }
        "H4" -> {
            def = player.H4atk
            atk = boss.H4atk
        }
        "H5" -> {
            def = player.H5atk
            atk = boss.H5atk
        }
        "Reach" -> {
            def = player.reachAtk
            atk = boss.reachAtk
        }
        "Infinite" -> {
            def = player.infinite_atk
            atk = boss.infinite_atk
        }
        "ODST" -> {
            def = player.ODSTatk
            atk = boss.ODSTatk
        }
        else -> {
            atk = 0
            def = 0
        }
    }

    atk = atk.coerceAtLeast(1)
    def = def.coerceAtLeast(1)
    val randomFactor = Random.nextDouble(0.9, 1.1)
    val accRoll = Random.nextDouble(0.0,1.0)
    if (accRoll <= eva){
        return 0
    }else{
        return (pow*Effectiveness(atk.toDouble(),def.toDouble())*randomFactor).toInt().coerceAtLeast(4)

    }
}

fun Effectiveness(atk:Double, def:Double): Double {

    if (atk/def > 2){
        return 2.0
    }
    else if(atk/def < 0.25){
        return 0.25
    }else{
        return atk/def
    }

}