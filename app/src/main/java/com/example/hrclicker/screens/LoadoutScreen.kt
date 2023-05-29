package com.example.hrclicker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.example.hrclicker.R
import com.example.hrclicker.dataBase.User
import com.example.hrclicker.dataBase.UserRepository
import com.example.hrclicker.functions.moveDescription
import kotlinx.coroutines.Dispatchers


@Composable
fun LoadoutScreen(navController: NavController, userRepository: UserRepository, player: User) {
    var moveArray:Array<String>? by remember { mutableStateOf(null) }
    var updates by remember { mutableStateOf(0) }

    var user:User? by remember { mutableStateOf(null) }
    LaunchedEffect(true, updates){
        userRepository.performDatabaseOperation(Dispatchers.IO){
            user = userRepository.allUsers()[0]
            moveArray = arrayOf(user!!.move1, user!!.move2, user!!.move3, user!!.move4)
        }
    }

    user?.let {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(Modifier.fillMaxWidth()) {
                moveArray?.forEachIndexed { index, x ->
                    Row(Modifier.align(Alignment.CenterHorizontally), verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            Modifier
                                .heightIn(50.dp)
                                .padding(8.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .fillMaxWidth(0.6f)
                                .background(Color(0, 0, 0, 50), shape = RoundedCornerShape(8.dp))
                        ) {
                            Text(
                                text = "Move ${index + 1}\n$x",
                                color = Color.White,
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        DropdownBox(index, user!!.moves, userRepository, index+1){updates++}
                        InfoBox(x)
                    }
                }
            }
        }
    }

}

@Composable
fun DropdownBox(index: Int, moves: Array<String>,userRepository: UserRepository,were:Int,update:()->Unit) {
    var expand by remember { mutableStateOf(false) }

    Box(
        Modifier
            .height(50.dp)
            .padding(8.dp)
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .width(50.dp)
            .background(Color(0, 0, 0, 50), shape = RoundedCornerShape(8.dp))
            .clickable { expand = true }
    ) {
        Box(Modifier.align(Alignment.Center)) {
            Image(
                painter = painterResource(id = R.drawable.baseline_autorenew_24),
                contentDescription = "swap move",
            )
            if (expand) {
                DropdownMenu(
                    expanded = expand,
                    onDismissRequest = { expand = false },
                ) {
                    moves.forEach { move ->
                        DropdownMenuItem(
                            onClick = {
                                userRepository.performDatabaseOperation(Dispatchers.IO){
                                    userRepository.swapMove(move,were)
                                }
                                update.invoke()
                                expand = false
                                // Handle move selection
                            }
                        ) {
                            Text(text = move)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoBox(moveName: String) {
    var popupControl by remember {
        mutableStateOf(false)
    }

    if (popupControl) {
        Popup(
            alignment = Alignment.Center,
        ) {
            Box(
                Modifier
                    .size(250.dp)
                    .background(color = Color.Black, shape = RoundedCornerShape(8.dp))
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Column(
                    Modifier.fillMaxSize(),
                    //verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = moveName,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        text = moveDescription(moveName),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                Button(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    onClick = { popupControl = false }
                ) {
                    Text(text = "ok")
                }
            }
        }
    }
        Box(
            Modifier
                .height(50.dp)
                .padding(8.dp)
                .border(
                    width = 1.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .width(50.dp)
                .background(Color(0, 0, 0, 50), shape = RoundedCornerShape(8.dp))
                .clickable { popupControl = true }
        ) {
            Box(Modifier.align(Alignment.Center)) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_question_mark_24),
                    contentDescription = "info",
                )
            }
        }
}

