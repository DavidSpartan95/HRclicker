package com.example.hrclicker.runnerData

data class Runner(
    var name:String,
    var score:Int,
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
}