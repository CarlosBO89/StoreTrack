package com.example.storetrack.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.storetrack.presentation.ui.screens.DetailsScreen
import com.example.storetrack.presentation.ui.screens.ItemsScreen
import com.example.storetrack.presentation.ui.screens.LogIn
import com.example.storetrack.presentation.ui.screens.Register
import com.example.storetrack.presentation.ui.screens.WindowCreateItem

@Composable
fun NavGraph(startDestination: String = Screen.Login.route) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Login.route) {
            LogIn(navController)
        }
        composable(Screen.Register.route) {
            Register(navController)
        }
        composable(Screen.Items.route) {
            ItemsScreen(navController)
        }
        composable(Screen.Create.route) {
            WindowCreateItem(navController)
        }
        composable(Screen.Details.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            DetailsScreen(navController, id)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavGraphPreview() {
    NavGraph()
}