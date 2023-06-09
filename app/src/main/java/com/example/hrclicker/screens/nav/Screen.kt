package com.example.hrclicker.screens.nav

sealed class Screen(val route: String) {
    object Home:Screen(route = "home_screen")
    object Score:Screen(route = "score_screen/{user}")
    object Battle:Screen(route = "battle_screen/{user}/{runner}/{category}")
    object Clicker:Screen(route = "clicker_screen")
    object Loadout:Screen(route = "loadout_screen/{user}")
}