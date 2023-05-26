package com.example.hrclicker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.example.hrclicker.dataBase.User
import com.example.hrclicker.dataBase.UserRepository
import com.example.hrclicker.functions.totalPoints
import com.example.hrclicker.runnerData.RunnerList
import com.example.hrclicker.ui.theme.HR_dark_blue
import com.example.hrclicker.ui.theme.HR_orange
import com.example.hrclicker.ui.theme.UserScoreCard
import com.example.hrclicker.ui.theme.grayTransparent
import com.google.gson.Gson

@Composable
fun ScoreScreen(navController: NavController, userRepository: UserRepository, user:User) {

    var popupControl by remember { mutableStateOf(false) }
    var expand by remember { mutableStateOf(false) }
    var selectCategory by remember { mutableStateOf("-???-") }
    var runnerSelected by remember { mutableStateOf("") }
    var userJson: String? = null
    var runnerJson: String? = null


    Surface(modifier = Modifier.fillMaxSize(),color = HR_dark_blue) {

        if (popupControl) {
            Popup(
                alignment = Alignment.Center,
            ) {
                Box(Modifier.fillMaxSize().background(grayTransparent)) {
                    Column(
                        Modifier
                            .background(color = HR_dark_blue)
                            .size(250.dp)
                            .fillMaxWidth()
                            .padding(2.dp)
                            .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(8.dp))
                            .padding(20.dp)
                            .align(Alignment.Center),
                        Arrangement.SpaceBetween,
                    ) {

                        Text(
                            text = "What category would you like to challenge $runnerSelected in?",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                            color = Color.White
                        )
                        Box(
                            Modifier
                                .width(150.dp)
                                .heightIn(50.dp)
                                .background(Color.Black)
                                .clickable { expand = true }
                                .align(CenterHorizontally)
                                .border(
                                    width = 1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        ) {
                            Text(
                                text = selectCategory,
                                color = Color.White,
                                modifier = Modifier.align(Alignment.Center)
                            )
                            DropdownMenu(
                                expanded = expand,
                                onDismissRequest = { expand = false},
                            ) {
                                for (x in clickButtons){
                                    DropdownMenuItem(
                                        onClick = {
                                            expand = false
                                            selectCategory = x.title
                                        }
                                    ) {
                                        Text(text = x.title)
                                    }
                                }
                            }
                        }

                        Row(Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate(route = "battle_screen/$userJson/$runnerJson")
                                }) {
                                Text(text = "Challenge")
                            }
                            Button(
                                onClick = { popupControl = false }
                            ) {
                                Text(text = "Close")
                            }
                        }

                    }
                }
            }
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(RunnerList.size) { index ->
                val reversedIndex = RunnerList.size - 1 - index
                val runner = RunnerList[reversedIndex]

                if (reversedIndex < RunnerList.size - 1 && totalPoints(user) > runner.score && totalPoints(user) < RunnerList[reversedIndex + 1].score) {
                    UserScoreCard(user)
                }

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                        .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (totalPoints(user) == runner.score){
                            Button(onClick = {
                                userJson = Gson().toJson(user)
                                runnerJson = Gson().toJson(runner)
                                runnerSelected = runner.name
                                popupControl = true
                            }) {
                                Text(text = "Challenge!")
                            }
                        }
                        Text(text = runner.name, color = HR_orange)
                        Text(text = "points: ${runner.score}", color = Color.White)
                    }
                }

                if (runner.name == "IL_noob" && totalPoints(user) < runner.score || runner.score == totalPoints(user)) {
                    UserScoreCard(user)
                }
            }
        }

    }
}