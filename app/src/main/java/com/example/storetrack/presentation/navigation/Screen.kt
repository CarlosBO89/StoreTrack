package com.example.storetrack.presentation.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("home")
    data object Register : Screen("login")
    data object Items : Screen("items")

    data object Create : Screen("create")


    data object Details : Screen("details/{id}") {
        fun createRoute(id: String) = "details/$id"
    }
}