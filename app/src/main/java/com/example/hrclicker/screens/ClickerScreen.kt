package com.example.hrclicker.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hrclicker.dataBase.User
import com.example.hrclicker.dataBase.UserRepository
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
    var cap by remember { mutableStateOf(50) }
    val context = LocalContext.current


    LaunchedEffect(true,p){
        println("update")
        userRepository.performDatabaseOperation(Dispatchers.IO){
            player = userRepository.allUsers()[0]
            println(player!!.H1atk)
        }
    }

    player?.let {

        Column(
            Modifier.fillMaxWidth(),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Text(
                text = "Total points: ${totalPoints(player!!)} \n current cap: $cap",
                color = Color.White,
            )
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
                        )
                        Box(
                            Modifier
                                .background(Color(8, 0, 30), shape = RoundedCornerShape(16.dp))
                                .size(100.dp)
                                .border(width = 1.dp,color = Color.White ,shape = RoundedCornerShape(16.dp))
                                .clickable {
                                    if (totalPoints(player!!) < cap){
                                        userRepository.performDatabaseOperation(Dispatchers.IO){
                                            userRepository.addPoints(clickButtons[index].title)
                                            p++
                                        }
                                    }else{
                                        Toast.makeText(context,"Challenge next runner to increase the cap!,",Toast.LENGTH_SHORT,).show()
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
