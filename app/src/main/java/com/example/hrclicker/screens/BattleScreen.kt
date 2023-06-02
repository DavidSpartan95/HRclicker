package com.example.hrclicker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.hrclicker.R
import com.example.hrclicker.dataBase.User
import com.example.hrclicker.dataBase.UserRepository
import com.example.hrclicker.functions.*
import com.example.hrclicker.runnerData.Runner
import com.example.hrclicker.screens.nav.Screen
import com.example.hrclicker.ui.theme.Character
import com.example.hrclicker.ui.theme.HR_dark_blue
import kotlinx.coroutines.Dispatchers

@Composable
fun BattleScreen(navController: NavController,userRepository: UserRepository, runner: Runner, user: User, category: String) {
    var humanHp by remember {
        mutableStateOf(user.HP)
    }
    var monsterHp by remember {
        mutableStateOf(runner.HP)
    }
    var isGameOver by remember {
        mutableStateOf(false)
    }
    var gameOverText by remember {
        mutableStateOf("")
    }
    var moveSelected by remember {
        mutableStateOf(0)
    }
    var playerMSG by remember {
        mutableStateOf("")
    }
    var monsterMSG by remember {
        mutableStateOf("")
    }
    var moveUsed by remember {
        mutableStateOf("")
    }
    var damage by remember {
        mutableStateOf(0)
    }
    var damageTaken = 0
    var boostUsed by remember {
        mutableStateOf(false)
    }
    var evasionBoostUsed by remember {
        mutableStateOf(false)
    }
    var boostMultiplier by remember {
        mutableStateOf(1.0)
    }
    var evasion by remember {
        mutableStateOf(0.0)
    }
    var healUsed by remember {
        mutableStateOf(false)
    }
    var heal by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(moveSelected) {
        if (moveSelected>0){
            monsterMSG = ""
            if (damage > 0){
                playerMSG = "${user.name} used $moveUsed ${runner.name} took $damage"
            }else if (damage == 0 && !boostUsed &&!evasionBoostUsed && !healUsed){
                playerMSG = "${user.name} used $moveUsed but missed!"
            }else if (boostUsed){
                playerMSG = "${user.name} used $moveUsed , Atk increased "
                boostUsed = false
            }else if (evasionBoostUsed){
                playerMSG = "${user.name} used $moveUsed , EVA increased "
                evasionBoostUsed = false
            }else if (healUsed){
                playerMSG = "${user.name} used $moveUsed , regained $heal"
                healUsed = false
            }

            delay(1000)
            damageTaken = DamageClacBoss(user,runner,category,evasion)
            if (damageTaken <= 0){
                monsterMSG = "${runner.name} missed!"
            }else{
                humanHp -= damageTaken
                monsterMSG = "${runner.name} used Reroute ${user.name} took $damageTaken"
            }

            if (humanHp <= 0){
                isGameOver = true
                gameOverText = "you lost"

            }else if(monsterHp <= 0) {
                isGameOver = true
                gameOverText = "you Won"
                userRepository.performDatabaseOperation(Dispatchers.IO){
                    if (user.cap == 12950){

                    }else{
                        userRepository.increaseCap(user.cap)
                    }
                }
            }
        }
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
        ,
        contentAlignment = Alignment.Center
    ) {
        if (isGameOver) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = gameOverText,
                    color = Color.White
                )
                Button(onClick = {
                    navController.navigate(route = "home_screen"){
                        popUpTo(Screen.Battle.route){
                            inclusive = true
                        }
                    }
                }) {
                    Text(text = "Done")
                }
            }
        } else {
            
            Row(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                // you can animate the boxes to move the characters
                Box {
                    Character(
                        name = user.name,
                        characterPainter = painterResource(id = R.drawable.ic_launcher_foreground),
                        currentHp = humanHp,
                        maxHp = 100
                    )
                }
                Box {
                    Character(
                        name = runner.name,
                        characterPainter = painterResource(id = R.drawable.ic_launcher_foreground),
                        currentHp = monsterHp,
                        maxHp = runner.HP
                    )
                }
            }
            //message box
            Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .heightIn(150.dp)
                        .background(HR_dark_blue)
                        .border(width = 1.dp, color = Color.White,)
                ) {
                    Column() {
                        Text(text = "Turn $moveSelected", color = Color.White)
                        Text(text = playerMSG, color = Color.White)
                        Text(text = monsterMSG, color = Color.White)
                    }
                }


            }
            //Action selection Box
            val buttonArr = arrayOf(
                Pair(user.move1,Alignment.TopStart),
                Pair(user.move2,Alignment.TopEnd),
                Pair(user.move3,Alignment.BottomStart),
                Pair(user.move4,Alignment.BottomEnd)
            )

            Box(
                Modifier
                    .fillMaxWidth()
                    .heightIn(150.dp)
                    .background(HR_dark_blue)
                    .align(Alignment.BottomCenter)
                    .border(width = 1.dp, color = Color.White,)
            ) {
                for (x in buttonArr){
                    Button(
                        onClick = {
                            if (monsterMSG != ""|| moveSelected == 0 ){
                                if (atkDouble(moveDescription(x.first)) > 1.0){
                                    boostMultiplier += (atkDouble(moveDescription(x.first))-1)
                                    boostUsed = true
                                    moveSelected++
                                }
                                else if (evaDouble(moveDescription(x.first)) > 1.0){
                                    evasion += (evaDouble(moveDescription(x.first))-1)
                                    evasionBoostUsed = true
                                    if (evasion> 0.7){
                                        evasion = 0.7
                                    }
                                    moveSelected++
                                }
                                else if (healInt(moveDescription(x.first)) > 0){
                                    heal = DamageClac(x.first,user,runner,category, boostMultiplier, true)
                                    healUsed = true
                                    val tempHeal = user.HP -humanHp
                                    humanHp += heal
                                    if (humanHp > user.HP){
                                        heal = tempHeal
                                        humanHp = user.HP
                                    }
                                    moveSelected++
                                }
                                else{
                                    println(atkDouble(moveDescription(x.first)))
                                    damage = DamageClac(x.first,user,runner,category, boostMultiplier)
                                    monsterHp -= damage
                                    moveSelected++
                                }
                                moveUsed = x.first
                            }
                        },
                        modifier = Modifier
                            .align(x.second)
                            .padding(15.dp)
                    ) {
                        Text(text = x.first)
                    }
                }
            }
        }
    }
}