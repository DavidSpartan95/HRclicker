package com.example.hrclicker.functions

import com.example.hrclicker.dataBase.User
import com.example.hrclicker.runnerData.Runner


fun DamageClac(player: User, boss: Runner, mode: String): Int{
    var pow = 10
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
    println(pow*Effectiveness(atk,def))
return  pow*Effectiveness(atk,def)

}

fun DamageClacBoss(player: User, boss: Runner, mode: String): Int{
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
    println(pow*Effectiveness(atk,def))
    return  pow*Effectiveness(atk,def)

}

fun Effectiveness(atk:Int, def:Int):Int{

    if (atk/def > 2){
        return 2
    }else{
        return atk/def
    }

}