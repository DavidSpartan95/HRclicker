package com.example.hrclicker.screens.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.hrclicker.screens.HomeScreen
import com.example.hrclicker.screens.ScoreScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Score.route
        ){
            ScoreScreen(navController = navController)
        }
    }
}
