package com.example.hrclicker.screens.nav

sealed class Screen(val route: String) {
    object Home:Screen(route = "home_screen")
    object Score:Screen(route = "score_screen")
}