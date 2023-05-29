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
import com.example.hrclicker.functions.DamageClac
import com.example.hrclicker.functions.DamageClacBoss
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
    var damage  by remember {
        mutableStateOf(0)
    }
    var damageTaken = 0
    LaunchedEffect(moveSelected) {
        if (moveSelected>0){
            playerMSG = "${user.name} used $moveUsed ${runner.name} took $damage"

            delay(300)
            damageTaken = DamageClacBoss(user,runner,category).coerceAtLeast(1)
            humanHp -= damageTaken
            monsterMSG = "${runner.name} used Reroute ${user.name} took $damageTaken"
            if (humanHp <= 0){
                isGameOver = true
                gameOverText = "you lost"

            }else if(monsterHp <= 0) {
                isGameOver = true
                gameOverText = "you Won"
                userRepository.performDatabaseOperation(Dispatchers.IO){
                    userRepository.increaseCap(user.cap)
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
            Box(
                Modifier
                    .fillMaxWidth()
                    .heightIn(150.dp)
                    .background(HR_dark_blue)
                    .align(Alignment.BottomCenter)
                    .border(width = 1.dp, color = Color.White,)
            ) {
                Button(
                    onClick = {
                        damage = DamageClac(user.move1,user,runner,category).coerceAtLeast(1)
                        moveUsed = user.move1
                        monsterHp -= damage
                        moveSelected++
                    },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(15.dp)
                ) {
                    Text(text = user.move1)
                }
                Button(
                    onClick = {
                        damage = DamageClac(user.move2,user,runner,category).coerceAtLeast(1)
                        moveUsed = user.move2
                        monsterHp -= damage
                        moveSelected++
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(15.dp)
                ) {
                    Text(text = user.move2)
                }
                Button(
                    onClick = {
                        damage = DamageClac(user.move3,user,runner,category).coerceAtLeast(1)
                        moveUsed = user.move3
                        monsterHp -= damage
                        moveSelected++
                    },
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(15.dp)
                ) {
                    Text(text = user.move3)
                }
                Button(
                    onClick = {
                        damage = DamageClac(user.move4,user,runner,category).coerceAtLeast(1)
                        moveUsed = user.move4
                        monsterHp -= damage
                        moveSelected++
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(15.dp)
                ) {
                    Text(text = user.move4)
                }
            }

        }
    }
}