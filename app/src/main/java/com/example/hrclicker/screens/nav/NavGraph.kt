package com.example.hrclicker.screens.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hrclicker.dataBase.User
import com.example.hrclicker.dataBase.UserRepository
import com.example.hrclicker.runnerData.Runner
import com.example.hrclicker.screens.BattleScreen
import com.example.hrclicker.screens.ClickerScreen
import com.example.hrclicker.screens.HomeScreen
import com.example.hrclicker.screens.ScoreScreen
import com.google.gson.Gson

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
            route = Screen.Score.route,
            arguments = listOf(
                navArgument("user"){
                    type = NavType.StringType
                }
            )

        ){ backStackEntry ->
            backStackEntry.arguments?.getString("user")?.let {
                    json ->
                val user = Gson().fromJson(json, User::class.java)
                ScoreScreen(
                    navController = navController,
                    userRepository = userRepository,
                    user = user
                )

            }
        }
        composable(
            route = Screen.Battle.route,
            arguments = listOf(
                navArgument("user"){
                    type = NavType.StringType
                },
                navArgument("runner"){
                    type = NavType.StringType
                },
                navArgument("category"){
                    type = NavType.StringType
                }
            )
        ){backStackEntry ->
            backStackEntry.arguments?.getString("user")?.let {
                    json ->
                val user = Gson().fromJson(json, User::class.java)
                backStackEntry.arguments?.getString("runner")?.let {
                    json ->
                    val runner = Gson().fromJson(json, Runner::class.java)
                    BattleScreen(
                        navController = navController,
                        runner = runner,
                        user = user,
                        category = backStackEntry.arguments?.getString("category")!!
                    )
                }
            }
        }
        composable(
            route = Screen.Clicker.route,
        ){
            ClickerScreen(navController = navController, userRepository = userRepository)
        }
    }
}
