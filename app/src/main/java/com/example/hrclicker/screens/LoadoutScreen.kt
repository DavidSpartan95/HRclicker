package com.example.hrclicker.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hrclicker.dataBase.User
import com.example.hrclicker.dataBase.UserRepository

@Composable
fun LoadoutScreen(navController: NavController, userRepository: UserRepository, user: User) {
    
    Column(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {

        Text(text = "Move 1: " + user.move1, color = Color.White, fontSize = 35.sp)
        Text(text = "Move 2: " + user.move2, color = Color.White, fontSize = 35.sp)
        Text(text = "Move 3: " + user.move3, color = Color.White, fontSize = 35.sp)
        Text(text = "Move 4: " + user.move4, color = Color.White, fontSize = 35.sp)
        
    }
    
}