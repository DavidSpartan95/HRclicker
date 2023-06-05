package com.example.hrclicker.screens

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hrclicker.dataBase.User
import com.example.hrclicker.dataBase.UserRepository
import com.example.hrclicker.functions.gameCap
import com.example.hrclicker.functions.moveUnlockCheck
import com.example.hrclicker.functions.pointsByGame
import com.example.hrclicker.functions.totalPoints
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.coroutineContext

class ClickButton(val title: String, val textSize: Int)

val clickButtons = arrayOf(
    ClickButton("CE", 50),
    ClickButton("H2", 50),
    ClickButton("H2A", 50),
    ClickButton("H3", 50),
    ClickButton("ODST", 35),
    ClickButton("Reach", 30),
    ClickButton("H4", 50),
    ClickButton("H5", 50),
    ClickButton("Infinite", 30),
)

@Composable
fun ClickerScreen(navController: NavController, userRepository: UserRepository) {

    var player: User? by remember { mutableStateOf(null) }
    var p by remember { mutableStateOf(0) }
    var cap: Int? by remember { mutableStateOf(null) }
    var count:Int by remember {
        mutableStateOf(0)
    }
    var points: Int by remember {
        mutableStateOf(1)
    }
    val context = LocalContext.current


    LaunchedEffect(true,p){
        println("update")
        userRepository.performDatabaseOperation(Dispatchers.IO){
            player = userRepository.allUsers()[0]
            cap = player!!.cap
        }
    }


    player?.let {

        Column(
            Modifier.fillMaxWidth(),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Text(
                text = "Total points: ${totalPoints(player!!)}\ncurrent cap: $cap",
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(20.dp))
            val pointArray = arrayOf(1,10,100)
            Row(Modifier.fillMaxWidth(),Arrangement.Center) {
                for (x in pointArray){
                    var color: Color = Color(8, 0, 30)
                    if (x == points){
                        color = Color(221, 136, 19)
                    }
                    Box(
                        Modifier
                            .background(color, shape = RoundedCornerShape(16.dp))
                            .size(75.dp)
                            .clickable { points = x }
                    ){
                        Text(
                            text = "+$x",
                            fontSize = 25.sp,
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(20.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ){
                items(clickButtons.size) {
                        index ->
                    Column(
                        Modifier.fillMaxWidth(),
                        Arrangement.Center,
                        Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Points:\n${pointsByGame(player!!, clickButtons[index].title)}",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                        Box(
                            Modifier
                                .background(Color(8, 0, 30), shape = RoundedCornerShape(16.dp))
                                .size(100.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .clickable {
                                    if (totalPoints(player!!) < cap!! && !gameCap(
                                            pointsByGame(
                                                player!!,
                                                clickButtons[index].title
                                            ), clickButtons[index].title
                                        )
                                    ) {
                                        userRepository.performDatabaseOperation(Dispatchers.IO) {
                                            var prevPoints = pointsByGame(player!!, clickButtons[index].title)
                                            var tempPoints = points
                                            if (totalPoints(player!!) + points > cap!!) {
                                                tempPoints = cap!! - totalPoints(player!!)
                                                println("temp is "+tempPoints)
                                            }
                                            userRepository.addPoints(clickButtons[index].title, tempPoints)
                                            moveUnlockCheck(
                                                pointsByGame(
                                                    player!!,
                                                    clickButtons[index].title
                                                ) + points,
                                                userRepository,
                                                clickButtons[index].title,
                                                context,
                                                prevPoints
                                            ) {
                                                p++
                                            }
                                        }
                                    } else {
                                        if (count < 5) {
                                            count++
                                            Toast
                                                .makeText(
                                                    context,
                                                    "Challenge next runner to increase the cap!,",
                                                    Toast.LENGTH_SHORT,
                                                )
                                                .show()

                                        }
                                    }
                                }

                        ) {
                            Text(
                                text = clickButtons[index].title,
                                fontSize = clickButtons[index].textSize.sp,
                                color = Color(221, 136, 19),
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }

}
