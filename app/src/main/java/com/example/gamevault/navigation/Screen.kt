package com.example.gamevault.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Library : Screen("library")
    object Details : Screen("details/{gameId}") {
        fun createRoute(gameId: Int) = "details/$gameId"
    }
    object Summary : Screen("summary")
}