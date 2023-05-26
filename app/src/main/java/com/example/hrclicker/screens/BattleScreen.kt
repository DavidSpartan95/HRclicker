package com.example.hrclicker.screens

import androidx.compose.foundation.background
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
import com.example.hrclicker.runnerData.Runner
import com.example.hrclicker.screens.nav.Screen
import com.example.hrclicker.ui.theme.Character

@Composable
fun BattleScreen(navController: NavController,runner: Runner, user: User) {
    var humanHp by remember {
        mutableStateOf(100)
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
    LaunchedEffect(key1 = monsterHp) {
        delay(300)
        println(monsterHp)
        humanHp -= 5
        if (humanHp <= 0){
            isGameOver = true
            gameOverText = "you lost"
        }else if(monsterHp <= 0) {
            isGameOver = true
            gameOverText = "you Won"
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
                        name = "You",
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
            Button(
                onClick = {
                    monsterHp -= 10
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(15.dp)
            ) {
                Text(text = "Attack")
            }
        }
    }

}