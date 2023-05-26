package com.example.hrclicker.ui.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hrclicker.dataBase.User
import com.example.hrclicker.functions.totalPoints

@Composable
fun UserScoreCard(user: User) {

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
            Text(text = user.name, color = HR_orange)
            Text(text = "points: ${totalPoints(user)}", color = Color.White)
        }
    }
}