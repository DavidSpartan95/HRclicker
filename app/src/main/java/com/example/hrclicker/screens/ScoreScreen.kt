package com.example.hrclicker.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hrclicker.runnerData.RunnerList
import com.example.hrclicker.ui.theme.HR_dark_blue
import com.example.hrclicker.ui.theme.HR_orange

@Composable
fun ScoreScreen(navController: NavController) {

    Surface(modifier = Modifier.fillMaxSize(),color = HR_dark_blue) {
        
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)){
            items(RunnerList.size) {

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
                        Text(text = RunnerList[it].name, color = HR_orange)
                        Text(text = "points: ${RunnerList[it].score}", color = Color.White)
                    }
                }
                
            }
        }
    }
}