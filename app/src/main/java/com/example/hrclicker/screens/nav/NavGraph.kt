package com.example.hrclicker.screens.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hrclicker.dataBase.UserRepository
import com.example.hrclicker.runnerData.Runner
import com.example.hrclicker.screens.BattleScreen
import com.example.hrclicker.screens.ClickerScreen
import com.example.hrclicker.screens.HomeScreen
import com.example.hrclicker.screens.ScoreScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    userRepository: UserRepository
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navController = navController, userRepository = userRepository)
        }
        composable(
            route = Screen.Score.route
        ){
            ScoreScreen(navController = navController)
        }
        composable(
            route = Screen.Battle.route,

        ){
            BattleScreen(navController = navController, runner = Runner("ZooPark",13556,165,150,100,100,""))
        }
        composable(
            route = Screen.Clicker.route
        ){
            ClickerScreen(navController = navController, userRepository = userRepository)
        }
    }
}
