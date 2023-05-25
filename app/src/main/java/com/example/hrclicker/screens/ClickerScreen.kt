package com.example.hrclicker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hrclicker.dataBase.UserRepository

class ClickButton(val title: String, val textSize: Int)

@Composable
fun ClickerScreen(navController: NavController, userRepository: UserRepository) {
    val clickButtons = arrayOf(
        ClickButton("CE", 50),
        ClickButton("CE", 50),
        ClickButton("CE", 50),
        ClickButton("CE", 50),
        ClickButton("CE", 50),
        ClickButton("CE", 50),
        ClickButton("CE", 50),
        ClickButton("CE", 50),
    )
    println(clickButtons[0].title)
    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ){
        items(clickButtons.size) { index ->
            Box(
                Modifier
                    .background(Color(8, 0, 30), shape = RoundedCornerShape(16.dp))
                    .size(100.dp)

            ) {
                Text(
                    text = clickButtons[index].title,
                    fontSize = 50.sp,
                    color = Color(221, 136, 19),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
