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
import com.example.hrclicker.functions.DamageClac
import com.example.hrclicker.functions.DamageClacBoss
import com.example.hrclicker.runnerData.Runner
import com.example.hrclicker.screens.nav.Screen
import com.example.hrclicker.ui.theme.Character
import com.example.hrclicker.ui.theme.HR_dark_blue

@Composable
fun BattleScreen(navController: NavController,runner: Runner, user: User, category: String) {
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
    var moveSelelcted by remember {
        mutableStateOf(0)
    }
    LaunchedEffect(moveSelelcted) {
        if (moveSelelcted>0){

            delay(300)
            humanHp -= DamageClacBoss(user,runner,category).coerceAtLeast(1)
            if (humanHp <= 0){
                isGameOver = true
                gameOverText = "you lost"
            }else if(monsterHp <= 0) {
                isGameOver = true
                gameOverText = "you Won"
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
                    .align(Alignment.Center),
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
            //Action selection Box
            Box(
                Modifier.fillMaxWidth()
                    .heightIn(200.dp)
                    .background(HR_dark_blue)
                    .align(Alignment.BottomCenter)
                    .border(width = 1.dp, color = Color.White,)
            ) {
                Button(
                    onClick = {
                        monsterHp -= DamageClac(user,runner,category).coerceAtLeast(1)
                        moveSelelcted++
                    },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(15.dp)
                ) {
                    Text(text = "Move 1")
                }
                Button(
                    onClick = {
                        monsterHp -= DamageClac(user,runner,category).coerceAtLeast(1)
                        moveSelelcted++
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(15.dp)
                ) {
                    Text(text = "Move 2")
                }
                Button(
                    onClick = {
                        monsterHp -= DamageClac(user,runner,category).coerceAtLeast(1)
                        moveSelelcted++
                    },
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(15.dp)
                ) {
                    Text(text = "Move 3")
                }
                Button(
                    onClick = {
                        monsterHp -= DamageClac(user,runner,category).coerceAtLeast(1)
                        moveSelelcted++
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(15.dp)
                ) {
                    Text(text = "Move 4")
                }
            }

        }
    }
}